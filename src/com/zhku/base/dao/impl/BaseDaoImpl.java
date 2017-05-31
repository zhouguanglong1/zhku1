package com.zhku.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zhku.base.dao.BaseDao;
import com.zhku.base.util.HqlHelper;
import com.zhku.base.util.PageBean;
import com.zhku.base.util.SqlHelper;


@Transactional
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> entityClass;
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}
	

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}
	
//	@SuppressWarnings("unchecked")
//	public T get(Long id) {
//		Assert.notNull(id, "id is required");
//		return (T) getSession().load(entityClass, id);
//	}

	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> get(String propertyName, Object value,String propertyName2, Object value2) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.hasText(propertyName2, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		Assert.notNull(value2, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ? and model." + propertyName2 + " = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);
		query.setParameter(1, value2);
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");

		try{
			Assert.notNull(value, "value is required");
			String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
			return getSession().createQuery(hql).setParameter(0, value).list();
		}catch(Exception e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListAndSort(String propertyName, Object value, String sortField, boolean sortWay) {
		Assert.hasText(propertyName, "propertyName must not be empty");

		String sortWayStr = "DESC";
		if(sortWay){
			sortWayStr = "ASC";
		}
		
		try{
			Assert.notNull(value, "value is required");
			String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
			hql += " ORDER BY model." + sortField + " " + sortWayStr;
			return getSession().createQuery(hql).setParameter(0, value).list();
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}
	
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}
	
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public void save(List<T> list) {
		Assert.notNull(list, "entityList is required");
		for(int i=0;i<list.size();i++){
			getSession().save(list.get(i));
		}
	}
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().flush();
		getSession().clear();
		getSession().update(entity);
	}
	public void update(List<T> list) {
		getSession().flush();
		getSession().clear();
	}
	
	/**
	 * 根据批量id修改其中的属性



	 * @param ids
	 * @param map 修改的属性已经修改后的值如：del:1
	 */
	public int update(Map<String,Object> map,final PK... pks){
		
		List<Object> args = new ArrayList<Object>();
		/*要修改的字段*/
		StringBuffer fields = new StringBuffer(" ");
		for(String key : map.keySet()){
			fields.append("o."+key+"=?,");
			args.add(map.get(key));
		}
		fields = fields.deleteCharAt(fields.length()-1);
		/*要修改的主键*/
		StringBuffer pksb = new StringBuffer(" ");
		
		pksb.append(batchAssemblyId(args, "o", pks));
		
		/*执行的HQL*/
		String hql = "update "+getEntityName()+" o set "
			+ fields.toString()+" where "+pksb.toString();
		int count = batchExecute(hql, args.toArray());
		return count;
	}
	
	/**
	 * 组装批量的主键预处理语句
	 * @param ids
	 * @param args 对应?的真实值 可以传空,表示不处理值的问题
	 * @param as 别名 
	 * @return
	 */
	protected String batchAssemblyId(List<Object> args,String as,PK... pks){
		StringBuffer pksb = new StringBuffer(" ");
		if(pks.length == 1){
			pksb.append("o."+getIdName()+"=?");
			args.add(pks[0]);
		}else{
			pksb.append(" o."+getIdName()+" in ( ");
			for(PK id:pks){
				pksb.append("?,");
				args.add(id);
			}
			pksb = pksb.deleteCharAt(pksb.length()-1);
			pksb.append(" )");
		}
		return pksb.toString();
	}
	/**
	 * 取得该操作对象的主键名.
	 */
	protected String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
	/**获取HIBERNATE查询所需要的实体名*/
	protected String getEntityName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getEntityName();
	}
	/**
	 * 执行HQL进行操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	@Override
	public Query createQuery(final String queryString, final Object... values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	public List<T> findBySQLQuery(String sql) {
		SQLQuery query = createSQLQuery(sql);
		List<T> list = query.list();
		return list;
	}
	/**
	 * 根据查询SQL与参数列表创建SQLQuery对象.
	 * @param sql
	 * @param values
	 * @return
	 */
	public SQLQuery createSQLQuery(final String sql,final Object... values){
		SQLQuery query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}
	
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	/**
	 * 按离线查询对象查询
	 * 
	 */
	public List<T> find(final DetachedCriteria detachedCriteria) {
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}
	/**
	 * 根据Criterion条件创建Criteria.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}
	
	public <E> List<E> findAll() {
		return null;
	}

	
	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}
	
	

	
	public int getTotalCount(String hql, Map map) {
		Query query = this.getSession().createQuery(hql);  
			
		if (map != null && map.size() != 0) {
			Iterator it = map.keySet().iterator();  
			while (it.hasNext()) {  
				Object key = it.next();  
				query.setParameter(key.toString(), map.get(key));  
			}  
		}

		Integer i = query.list().size();  

		return i;  
	}  
	
	public int getTotalCount(String sql) {
		Query query = this.getSession().createSQLQuery(sql);  
		Integer i = query.list().size();  

		return i;  
	}  
	
	
	
	/* (non-Javadoc)
	 * @see com.cnooc.dao.common.BaseDao#getCountBySqlDirectly(java.lang.String)
	 */
	public int getCountBySqlDirectly(String sql) {
		return (Integer)getSession().createSQLQuery(sql).addScalar("expectedCount", Hibernate.INTEGER).list().get(0);
	}
	
	

	
	public void updateWorkflowBySql(String hql) {
		getSession().createQuery(hql).executeUpdate();
	}
	
	public void updateBySql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 根据主键获取流程信息
	 * 
	 * @param workflowId
	 * @return
	 */
	public T getWorkflowById(String workflowId) {
		String hql = "from " + entityClass.getName() + " as model where to_char(model.workflowId) = ? ";
		return (T) getSession().createQuery(hql).setParameter(0, workflowId).uniqueResult();
	}
	
	/**
	 * 根据编码删除流程
	 * 
	 * @param workflowId
	 */
	public void deleteWorkflowRecord(String workflowId) {
		String hql = "Delete " + entityClass.getName() + " where to_char(workflowId) = ?";
		getSession().createQuery(hql).setParameter(0, workflowId).executeUpdate();
	}
	
	/**
	 * 根据流程编码获取流程审批意见
	 * 
	 * @param workflowId
	 * @return
	 */
	public List<T> getWorkflowActionList(String workflowId) {
		String hql = " from " + entityClass.getName() + " where to_char(workflowId) = ? order by operateDate ";
		return getSession().createQuery(hql).setParameter(0, workflowId).list();
	}
	
	/**
	 * 根据流程编码获取流程操作过程
	 * 
	 * @param workflowId
	 * @return
	 */
	public List<T> getWorkflowProcessList(String workflowId) {
		String hql = " from " + entityClass.getName() + " where to_char(workflowId) = ? order by processId ";
		return getSession().createQuery(hql).setParameter(0, workflowId).list();
	}	
	
	/**
	 * 获取操作过程最后结束时间作为新的操作的开始时间
	 * 
	 * @param workflowId
	 * @param workflowType
	 */
	public Date getProcessEndDate(String workflowId, String workflowType) {
		String sql = " select to_char(t.completeddate, 'yyyy-MM-dd HH24:mi:ss') as completeddate from t_" + workflowType + "workflowprocess t, "
			       + " (select max(processid) as processid from t_" + workflowType + "workflowprocess where to_char(workflowid) = '" + workflowId + "' ) mt "
			       + " where t.processid = mt.processid "
			       + " and to_char(t.workflowId) = '" + workflowId + "'";
		Query query = getSession().createSQLQuery(sql).addScalar("completeddate", Hibernate.STRING);
		
		List<Object> dataList = query.list();
		String object = dataList.get(0).toString();
		
		try {
			Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object));
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	public List<T> findByQuery(String hql, Map map) {
		Query query = createQuery(hql);
//		query.setParameter(0, map.get("str"));
		List<T> list = createQuery(hql).list();
		return list;
	}
	public Long countByQuery(String hql, Map map) {
		Query query = createQuery(hql);
		return (Long) getSession().createQuery(hql).uniqueResult();
	}


	
	
	
	public PageBean getPageBean(int pageNum, int pageSize, HqlHelper hqlHelper) {
		List<Object> parameters = hqlHelper.getParameters();
		Session session = sessionFactory.getCurrentSession();
		// 查询本页的数据列表
		Query query = session.createQuery(hqlHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		// 设置分页参数
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		// 执行查询
		List list = query.list();
		// 查询总记录的数量
		Query countQuery = session.createQuery(hqlHelper.getQueryCountHql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult();
		return new PageBean(list, count.intValue());
	}
	
	/**
	 * 根据字典类型查找字典项
	 * @param baseType
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findBaseData(String baseType){
		List<Map<String, Object>> listMap=createSQLQuery(
				"select b.id,b.basevalue,b.basename from mm_sc_Base b where b.BASETYPE=? and b.STATU=0 ORDER BY b.SORT", baseType)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		return listMap;
	}
	
	public PageBean getPageBeans(HqlHelper hqlHelper) {
		List<Object> parameters = hqlHelper.getParameters();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hqlHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		// 执行查询
		List list = query.list();
		// 查询总记录的数量
		Query countQuery = session.createQuery(hqlHelper.getQueryCountHql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult();
		return new PageBean(list, count.intValue());
	}
	
	public PageBean getPageBeans(int pageNum, int pageSize, SqlHelper sqlHelper) {
		List<Object> parameters = sqlHelper.getParameters();
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlHelper.getQueryListSql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNum!=0&&pageSize!=0){
			query.setFirstResult((pageNum -1) * pageSize);
			query.setMaxResults(pageSize);
		}
		
		//判断是否查询条件为  *
		if(sqlHelper.getResout() == null || sqlHelper.getResout().length() == 0){
			//判断是否有设置实体类型
			if(sqlHelper.getTableClass() != null){
				query.addEntity(sqlHelper.getAlias(), sqlHelper.getTableClass());
			}
		}else{   //多表查询
			//判断字段中，是否含有   .* 的查询字段，并提供相应的class对象封装
			if(sqlHelper.getPosttingClass() != null && sqlHelper.getPosttingClass().size() > 0){
				String queryField = sqlHelper.getResout();
				for(Map.Entry<String, Object> entry : sqlHelper.getPosttingClass().entrySet()){
					query.addEntity(entry.getKey().replace(".*", ""), (Class)entry.getValue());
					//移除查询字段中的.*字符串
					queryField = queryField.replace(entry.getKey(), "");
				}
				
				//循环设置其他查询字段的封装类型
				String[] array = queryField.split(",");
				for (int i = 0; i < array.length; i++) {
					if(!"".equals(array[i].trim())){
						String parp = array[i];
						if(array[i].indexOf(".") != -1){
							parp = parp.substring(parp.indexOf(".") + 1, parp.length());
						}
						query.addScalar(parp);
					}
				}
			}
		}
		
		// 执行查询
		List list = query.list();
		// 查询总记录的数量
		SQLQuery countQuery = session.createSQLQuery(sqlHelper.getQueryCountSql());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Integer count = (Integer) countQuery.uniqueResult();
		return new PageBean(list, count.intValue());
	}

	@Override
	public List<Object[]> findSQLQuery(String sql) {
		SQLQuery query = createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public List<Object> findOneSQLQuery(String sql) {
		SQLQuery query = createSQLQuery(sql);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public void executeSqlUpdate(String sql) throws Exception {
		createSQLQuery(sql)
		.executeUpdate();
	}


	/**
	 * 自定义SQL查询，通过传入执行的SQL，和查询参数，返回查询结果数据
	 */
	@Override
	public List<Map<String, Object>> customSqlQuery(String sql,
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		SQLQuery query = getSession().createSQLQuery(sql);
	    //循环设置查询参数
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		//设置结果集返回类型为List<Map<String, Object>>形式
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		return query.list();
	}
	
	@Override
	public List<T> findByHqlQuery(String hql, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		for(Map.Entry<String, Object> entry : paramMap.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.list();
	}

	@Override
	public List<Object[]> findByHqlQuery(String hql, List<Object> paramMap) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		for(int i=0; i<paramMap.size(); i++){
			query.setParameter(i, paramMap.get(i));
		}
		
		return query.list();
	}
	@Override
	public List<Object[]> findPageByQuerySQL(String sql, PageBean pageBean) {
		Query query = this.getSession().createSQLQuery(sql);
		
		if(pageBean!=null){
			int currentPage = Integer.parseInt(pageBean.getCurrentPage());
			int pageSize = Integer.parseInt(pageBean.getPageSize());

			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

}
