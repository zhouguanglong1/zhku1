package com.zhku.community.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.shop.Category;

@Repository
public class CategoryDao extends BaseDaoImpl<Category, Long> {

	public List<Category> findByIdOrName(Long id, String cname) {
		Session session = getSession();
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from shop_category c where 1=1 ");
		if(id!=null&&!"".equals(id)){
			sb.append("and c.id = '"+id+"'");
		}else if(cname==null&&!"".equals(cname)){
			sb.append("and c.cname like '%"+cname+"%'");
		}
		/*if((id!=null&&!"".equals(id))&&(cname!=null&&!"".equals(cname))){
			sb.append("where c.id = '"+id+"' and c.cname like '%"+cname+"%'");
		}else if((id==null||"".equals(id))&&(cname!=null&&!"".equals(cname))){
			sb.append("where c.cname like '%"+cname+"%'");
		}else if((id!=null&&!"".equals(id))&&(cname==null||"".equals(cname))){
			sb.append("where c.id = '"+id+"'");
		}*/
		SQLQuery q = session.createSQLQuery(sb.toString()).addEntity(Category.class);
		//List<Category> list = this.findBySQLQuery(sb.toString());
		List<Category> list = q.list();
		return list;
	}

	public boolean exist(Long cid) {
		// TODO Auto-generated method stub
		String sql = "select cs.id from shop_categorySecond cs where cs.category_id = '"+cid+"'";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	
}
