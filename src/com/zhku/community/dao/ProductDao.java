package com.zhku.community.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.shop.Product;

@Repository
public class ProductDao extends BaseDaoImpl<Product, Long> {

	public SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Integer findCount() {
		String sql = "select p.id from shop_product p";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}else{
			return 0;
		}
		
	}

	public List<Product> findByPage(int begin, int limit1) {
		// TODO Auto-generated method stub
		/*String sql = "select p.image,p.is_hot,p.market_price,p.pdate,p.pdesc,"
				   + "p.pname,p.shop_price,"
				   + "p.address from shop_product p limit "+begin+","+limit1+";";*/
		Session session = getSession();
		Query q = session.createQuery("select p from Product as p order by p.pdate desc");
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		List<Product> list = q.list();
		//List<Product> list = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Product.class)).list();
		return list;
	}

	public List<Product> findByPage() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	public Integer findCountByCid(Long cid) {
		String sql = "SELECT p.pname FROM shop_product p "
				+ "LEFT JOIN shop_categorysecond cs ON cs.id = p.categorySecond_id "
				+ "LEFT JOIN shop_category c ON c.id = cs.category_id "
				+ "WHERE c.id = '"+cid+"'";
					 
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit1, Long cid,Integer page) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "SELECT p.* FROM shop_product p "
				+ "LEFT JOIN shop_categorysecond cs ON cs.id = p.categorySecond_id "
				+ "LEFT JOIN shop_category c ON c.id = cs.category_id "
				+ "WHERE c.id = ? order by p.id";
		//sb.append(" ORDER BY p.id");
		SQLQuery q = session.createSQLQuery(sql).addEntity(Product.class);
		q.setParameter(0, cid);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		//q.setResultTransformer(Transformers.aliasToBean(Product.class));
		List<Product> list = q.list();
		return list;
	}

	public Integer findCountByCsid(Long csid) {
		String sql = "SELECT p.pname FROM shop_product p "
				+ "LEFT JOIN shop_categorysecond cs ON cs.id = p.categorySecond_id "
				+ "WHERE cs.id = '"+csid+"'";
					 
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<Product> findByPageAndCsid(int begin, int limit1, Long csid,
			Integer page) {
		Session session = getSession();
		String sql = "SELECT p.* FROM shop_product p "
				+ "LEFT JOIN shop_categorysecond cs ON cs.id = p.categorySecond_id "
				+ "WHERE cs.id = ? order by p.id";
		SQLQuery q = session.createSQLQuery(sql).addEntity(Product.class);
		q.setParameter(0, csid);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		//q.setResultTransformer(Transformers.aliasToBean(Product.class));
		List<Product> list = q.list();
		
		return list;
	}

	public List<Product> findByInput(String pname, String shop_price_from,
			String shop_price_to, String pdateStart, String pdateEnd,
			String address) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT p.* FROM shop_product p where 1=1 ");
		if(pname!=null && !"".equals(pname)){
			sb.append("and p.pname like '%"+pname+"%'");
		}
		if(shop_price_from!=null && !"".equals(shop_price_from)){
			sb.append(" and p.shop_price >= '"+shop_price_from+"'");
		}
		if(shop_price_to!=null && !"".equals(shop_price_to)){
			sb.append(" and p.shop_price <= '"+shop_price_to+"'");
		}
		if(pdateStart!=null && !"".equals(pdateStart)){
			sb.append(" and p.pdate >= '"+pdateStart+"'");
		}
		if(pdateEnd!=null && !"".equals(pdateEnd)){
			sb.append(" and p.pdate <= '"+pdateEnd+"'");
		}
		if(address!=null && !"".equals(address)){
			sb.append(" and p.address like '%"+address+"%'");
		}
		
		sb.append(" order by p.pdate");
		
		List<Product> list = session.createSQLQuery(sb.toString()).addEntity(Product.class).list();
		return list;
	}

	public Integer findCountByPname(String srchtxt) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT p.id FROM shop_product p where 1=1 ");
		if(srchtxt!=null&&!"".equals(srchtxt)){
			sb.append(" and p.pname like '%"+srchtxt+"%'");
			
		}
		//System.out.println("sql:--------------"+sb.toString());
		
		List<Object> list = this.findOneSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit1, String srchtxt,
			Integer page) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT p.* FROM shop_product p where 1=1 ");
		if(srchtxt!=null&&!"".equals(srchtxt)){
			sb.append(" and p.pname like '%"+srchtxt+"%'");
		}
				
		//sb.append(" ORDER BY p.id");
		SQLQuery q = session.createSQLQuery(sb.toString()).addEntity(Product.class);
		//q.setParameter(0, srchtxt);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		//q.setResultTransformer(Transformers.aliasToBean(Product.class));
		List<Product> list = q.list();
		return list;
	}

	
	
	
}
