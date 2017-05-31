package com.zhku.community.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.shop.Category;
import com.zhku.community.bean.shop.CategorySecond;
import com.zhku.community.bean.shop.Product;

@Repository
public class CategorySecondDao extends BaseDaoImpl<CategorySecond, Long> {

	public Integer findCount() {
		// TODO Auto-generated method stub
		String sql = "select cs.id from shop_categorysecond cs";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<CategorySecond> findByPage(int begin, int limit1) {
		Session session = getSession();
		/*StringBuffer sb = new StringBuffer();
		sb.append("select cs.*,c.cname from shop_categorysecond cs ");
		sb.append("left join shop_category c on c.id = cs.category_id ");
		sb.append("order by cs.id");*/
		SQLQuery q = session.createSQLQuery("select cs.* from shop_categorysecond cs order by cs.id").addEntity(CategorySecond.class);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		
		List<CategorySecond> list = q.list();
		//List<Product> list = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Product.class)).list();
		return list;
	}

	public boolean exit(Long csid) {
		// TODO Auto-generated method stub
		String sql = "SELECT p.id FROM shop_product p WHERE p.categorySecond_id = '"+csid+"'";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	public List<CategorySecond> findByIdOrName(Long id, String strName) {
		Session session = getSession();
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from shop_categorySecond cs ");
		if((id!=null&&!"".equals(id))&&(strName!=null&&!"".equals(strName))){
			sb.append("where cs.id = '"+id+"' and cs.csname like '%"+strName+"%'");
		}else if((id==null||"".equals(id))&&(strName!=null&&!"".equals(strName))){
			sb.append("where cs.csname like '%"+strName+"%'");
		}else if((id!=null&&!"".equals(id))&&(strName==null||"".equals(strName))){
			sb.append("where cs.id = '"+id+"'");
		}
		SQLQuery q = session.createSQLQuery(sb.toString()).addEntity(CategorySecond.class);
		//List<Category> list = this.findBySQLQuery(sb.toString());
		List<CategorySecond> list = q.list();
		return list;
	}

	
}
