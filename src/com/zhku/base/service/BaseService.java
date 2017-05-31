package com.zhku.base.service;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import com.zhku.base.util.HqlHelper;
import com.zhku.base.util.PageBean;
import com.zhku.base.util.SqlHelper;
import com.zhku.community.bean.sys.User;




public interface BaseService<T, PK extends Serializable> {

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);
	
//	/**
//	 * 根据ID获取实体对象.
//	 * 
//	 * @param id
//	 *            记录ID
//	 * @return 实体对象
//	 */
//	public T get(Long id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);
	
	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<T> get(PK[] ids);
	
	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);
	/**
	 * 根据两个属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public List<T> get(String propertyName, Object value, String propertyName2, Object value2);
	
	
	/**
	 * 根据属性名和属性值获取实体对象集合.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);
	
	/**
	 * 根据属性名和属性值获取实体对象集合.并排序
	 */
	public List<T> getListAndSort(String propertyName, Object value, String sortField, boolean sortWay);

	/**
	 * 获取所有实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();
	
	/**
	 * 获取所有实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue, Object newValue);
	
	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public PK save(T entity);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);
	/**
	 * 更新批量实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(List<T> list);
	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);
	
	/**
	 * 刷新session.
	 * 
	 */
	public void flush();

	/**
	 * 清除Session.
	 * 
	 */
	public void clear();
	
	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	
	/**
	 * 根据Pager和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	
	public int getTotalCount(String hql, Map map);
	public int getTotalCount(String sql);
	public List<T> findByQuery(String hql, Map map);

	
	/**
	 * 根据编码删除流程
	 * 
	 * @param workflowId
	 */
	public void deleteWorkflowRecord(String workflowId);
	
	/* 根据SQL语句更新数据
	 * 
	 * @param sql
	 */
	public void updateBySql(String sql);
	
	
	public Long countByQuery(String hql, Map map);
	

	

	
	
	/**
	 * @param pageNum 当前第几页  
	 * @param pageSize 每页显示的记录数 
	 * @param hqlHelper HQL工具
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, HqlHelper hqlHelper);
	public PageBean getPageBean(int pageNum, int pageSize, SqlHelper sqlHelper);
	
	/**
	 * 根据查询SQL与参数列表创建SQLQuery对象.
	 * @param sql
	 * @param values
	 * @return
	 */
	public SQLQuery createSQLQuery(final String sql,final Object... values);
	/**
	 * 根据字典类型查找字典项
	 * @param baseType
	 * @return
	 */
	List<Map<String, Object>> findBaseData(String baseType);
	/**
	 * 存日志导数库
	 * @param opid
	 * @param op
	 * @param type
	 * @param func
	 * @param message
	 */
	void logDB(long opid, String op, String type, String func, String message);
	List<Object> findCompanyCodeByBase(String baseType);
	List<Object[]> findSQLQuery(String sql);
	List<Object> findOneSQLQuery(String sql);
	void executeSqlUpdate(String sql) throws Exception;
	String getUserCompanyHQL(String company, String phone, User user,int type);
	String getUserCompanySQL(String company, String phone, User user,int type);
	String setSchedulePermission(User user,String alias);
	public  List<Integer> getUserRole(User user);
	
	// 获取Base字典key value对应的MAP
	public Map<String, String> getBaseValueMap(String type);
	// 获取Base字典key value对应的MAP
	public Map<String, String> getBaseNameValueMap(String type);
	
	
	
	/**
	 * 自定义SQL查询，通过传入执行的SQL，和查询参数，返回查询结果数据
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> customSqlQuery(String sql, Map<String, Object> paramMap);
	public List<T> findByHqlQuery(String hql, Map<String, Object> paramMap);
	public List<Object[]> findByHqlQuery(String hql, List<Object> paramMap);
	public List<Object[]> findPageByQuerySQL(String sql, PageBean pageBean);
}
