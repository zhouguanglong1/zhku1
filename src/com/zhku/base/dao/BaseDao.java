package com.zhku.base.dao;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import com.zhku.base.util.HqlHelper;
import com.zhku.base.util.PageBean;
import com.zhku.base.util.SqlHelper;
import com.zhku.community.bean.sys.User;



public interface BaseDao<T, PK extends Serializable> {
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
	public List<T> get(String propertyName, Object value,String propertyName2, Object value2);
	
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
	 * 
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
	 * 保存批量实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public void save(List<T> list);
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
	 * 根据批量id修改其中的属性



	 * @param ids
	 * @param map 修改的属性已经修改后的值如：del:1
	 */
	public int update(Map<String,Object> map,final PK... pks);
	
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
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) ;
	public List<T> findBySQLQuery(String sql);
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) ;
	/**
	 * 按离线查询对象查询
	 * 
	 */
	public List<T> find(final DetachedCriteria detachedCriteria) ;
	/**
	 * 根据Criterion条件创建Criteria.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) ;
	
	/**
	 * 获取查询所有记录数
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public int getTotalCount(String hql, Map map);
	public int getTotalCount(String sql);
	public int getCountBySqlDirectly(String sql);

	public List<T> findByQuery(String hql, Map map);
	public void updateBySql(String sql);

	
	
	public Long countByQuery(String hql, Map map);
	

	
	/**
	 * 根据查询SQL与参数列表创建SQLQuery对象.
	 * @param sql
	 * @param values
	 * @return
	 */
	public SQLQuery createSQLQuery(final String sql,final Object... values);
	PageBean getPageBean(int pageNum, int pageSize, HqlHelper hqlHelper);
	PageBean getPageBeans(HqlHelper hqlHelper);

	List<Map<String, Object>> findBaseData(String baseType);

	Query createQuery(String queryString, Object... values);

	List<Object[]> findSQLQuery(String sql);
	public List<Object> findOneSQLQuery(String sql);

	public void executeSqlUpdate(String sql)throws Exception;


	public PageBean getPageBeans(int pageNum, int pageSize, SqlHelper sqlHelper);


	

	/**
	 * 自定义SQL查询，通过传入执行的SQL，和查询参数，返回查询结果数据
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> customSqlQuery(String sql,
			Map<String, Object> paramMap);
	public List<T> findByHqlQuery(String hql, Map<String, Object> paramMap);
	public List<Object[]> findByHqlQuery(String hql, List<Object> paramMap);
	public List<Object[]> findPageByQuerySQL(String sql, PageBean pageBean);
}
