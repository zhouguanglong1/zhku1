package com.zhku.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于辅助拼接HQL语句的工具类
 * @author HuMeng
 */
public class HqlHelper {
	private String fromClause;//From子句
	private String whereClause="";//where子句
	private String orderByClause="";//OrderBy子句
	private List<Object> parameters=new ArrayList<Object>();//参数列表�?
	
	private String resout;
	private String otherTable;
	private String alias;
	private String className;
	/**
	 * 生成From子句
	 * @param clazz
	 * @param alias 别名
	 */
	public HqlHelper(Class clazz,String alias) {
		fromClause="FROM "+clazz.getName()+" "+alias;
	}
	public HqlHelper(Class clazz,String alias,String resout,String otherTable) {
		this.alias=alias;
		this.resout=resout;
		this.otherTable=otherTable;
		this.className=clazz.getName();
		String dou="";
		if(otherTable!=null&&!"".equals(otherTable)){
			dou=",";
		}
		
		fromClause="SELECT "+resout+" FROM "+clazz.getName()+" "+alias + dou + otherTable;
		
	}
	/**
	 * 拼接where子句
	 */
	public HqlHelper addWhereCondition(String condition,Object... params){
		//拼接where子句
		if (whereClause.length()==0) {
			whereClause=" WHERE "+condition;
		} else {
			whereClause+=" AND "+condition;
		}
		//处理参数
		if (params!=null&&params.length>0) {
			for (Object param : params) {
				parameters.add(param);
			}
		}
		return this;
	}
	//如果第一个参数�?为true，就拼接where子句
	public HqlHelper addWhereCondition(boolean append,String condition,Object... params){
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}
	// 拼接OrderBy子句
	public HqlHelper addOrderProperty(String propertyName, boolean isAsc) {
		// 拼接OrderBy子句
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName+(isAsc?" ASC":" DESC");
		} else {
			orderByClause += ", " + propertyName+(isAsc?" ASC":" DESC");
		}
		return this;
	}
	// 如果第一个参数�?为true，就拼接OrderBy子句
	public HqlHelper addOrderProperty(boolean append, String propertyName, boolean isAsc) {
		if (append) {
			addOrderProperty(propertyName, isAsc);
		}
		return this;
	}
	//获取查询数据列表的Hql语句
	public String getQueryListHql(){
		return fromClause+whereClause+orderByClause;
	}
	//获取查询总记录数的Hql语句，不带Order By子句�?
	public String getQueryCountHql(){
	     if(resout!=null&&!"".equals(resout)&&otherTable!=null){
	    	String dou="";
			if(otherTable!=null&&!"".equals(otherTable)){
				dou=",";
			}
	    	return "SELECT COUNT(distinct "+alias+") "+"FROM "+className+" "+alias+ dou +otherTable+whereClause;
//	     return "SELECT COUNT(distinct "+alias+") "+"FROM "+className+" "+alias+ dou +otherTable+whereClause;
	     }else
		    return "SELECT COUNT(*) "+fromClause+whereClause;
	}
	//获取查询某些属�?总数的Hql语句，不带Order By子句�?
	public String getQueryPsCountHql(String pa){
		return "SELECT "+pa+" "+fromClause+whereClause;
	}
	//获取参数列表
	public List<Object> getParameters(){
		return parameters;
	}

}