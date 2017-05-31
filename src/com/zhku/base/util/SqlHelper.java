package com.zhku.base.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于辅助拼接SQL语句的工具类
 * @author dyy
 */
public class SqlHelper {
	private String fromClause;//From子句
	private String whereClause="";//where子句
	private String orderByClause="";//OrderBy子句
	private String groupByClause="";//GroupBy子句
	private List<Object> parameters=new ArrayList<Object>();//参数列表�?
	
	private String resout;
	private String otherTable;
	private String alias;
	private String tableName;
	private Class tableClass;          //用于单表查询，标识查询字段为  * 字符
	private Map<String, Object> posttingClass = null;     //用于多表查询时，标识查询字段中含�? .* 的字�?
	
	/**
	 * 生成From子句
	 * @param tableName 表名
	 * @param alias 别名
	 */
	public SqlHelper(String tableName,String alias) {
		this.alias=alias;
		this.tableName=tableName;
		fromClause="SELECT * FROM "+tableName+" "+alias;
	}
	public SqlHelper(String tableName,String alias,String resout,String otherTable) {
		this.alias=alias;
		this.resout=resout;
		this.otherTable=otherTable;
		this.tableName=tableName;
		String dou="";
		if(otherTable!=null&&!"".equals(otherTable)){
			dou=",";
		}
		
		fromClause="SELECT "+resout+" FROM "+tableName+" "+alias + dou + otherTable;
		
	}
	/**
	 * 拼接where子句
	 */
	public SqlHelper addWhereCondition(String condition,Object... params){
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
	public SqlHelper addWhereCondition(boolean append,String condition,Object... params){
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}
	// 拼接OrderBy子句
	public SqlHelper addOrderProperty(String propertyName, boolean isAsc) {
		// 拼接OrderBy子句
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName+(isAsc?" ASC":" DESC");
		} else {
			orderByClause += ", " + propertyName+(isAsc?" ASC":" DESC");
		}
		return this;
	}
	// 如果第一个参数�?为true，就拼接OrderBy子句
	public SqlHelper addOrderProperty(boolean append, String propertyName, boolean isAsc) {
		if (append) {
			addOrderProperty(propertyName, isAsc);
		}
		return this;
	}
	// 拼接Group By子句
	public SqlHelper addGroupProperty(String propertyName) {
		// 拼接Group By子句
		if (groupByClause.length() == 0) {
			groupByClause = " GROUP BY " + propertyName;
		} else {
			groupByClause += ", " + propertyName;
		}
		return this;
	}
	// 如果第一个参数�?为true，就拼接Group By子句
	public SqlHelper addGroupProperty(boolean append, String propertyName) {
		if (append) {
			addGroupProperty(propertyName);
		}
		return this;
	}
	//获取查询数据列表的Sql语句
	public String getQueryListSql(){
		return fromClause+whereClause+groupByClause+orderByClause;
	}
	//获取查询总记录数的Sql语句，不带Order By子句�?
	public String getQueryCountSql(){
	     if(resout!=null&&!"".equals(resout)&&otherTable!=null){
	    	String dou="";
			if(otherTable!=null&&!"".equals(otherTable)){
				dou=",";
			}
			
			if(groupByClause.length() == 0){
				if("".equals(this.alias) || this.alias == null){
					return "SELECT COUNT(*) "+"FROM "+tableName+" "+this.alias+ dou +otherTable+whereClause;
				}else{
					return "SELECT COUNT(distinct "+this.alias+") "+"FROM "+tableName+" "+this.alias+ dou +otherTable+whereClause;
				}
			}else{
				return "SELECT COUNT(*) FROM ("+fromClause+whereClause+groupByClause+") b";
			}
	     }else
	    	 if(groupByClause.length() == 0){
	    		 return "SELECT COUNT(*) FROM "+tableName+" "+alias+whereClause;
	    	 }else{
	    		 return "SELECT COUNT(*) FROM ("+fromClause+whereClause+groupByClause+") b";
	    	 }
	}
	/*//获取查询某些属�?总数的Hql语句，不带Order By子句�?
	public String getQueryPsCountSql(String pa){
		return "SELECT "+pa+" "+fromClause+whereClause;
	}*/
	//获取参数列表
	public List<Object> getParameters(){
		return parameters;
	}
	
	/**
	 * 添加对查询字段用  .* 的class对象的标�?
	 * @param tableAlias
	 * @param tableClass
	 */
	public void addQueryClass(String fieldAlias, Class fieldClass){
		if(this.posttingClass == null ){
			this.posttingClass = new LinkedHashMap<String, Object>(); 
		}
		
		this.posttingClass.put(fieldAlias + ".*", fieldClass);
	}
	
	public Class getTableClass() {
		return tableClass;
	}
	public void setTableClass(Class tableClass) {
		this.tableClass = tableClass;
	}
	public String getResout() {
		return resout;
	}
	public String getAlias() {
		return alias;
	}
	public Map<String, Object> getPosttingClass() {
		return posttingClass;
	}
}