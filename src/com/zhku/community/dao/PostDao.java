package com.zhku.community.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.bean.shop.Product;

@Repository
public class PostDao extends BaseDaoImpl<Post, Long> {

	public List<Post> findByUser(Long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select p.* from lt_post p where p.user_id = '"+id+"'";
		List<Post> list = session.createSQLQuery(sql).addEntity(Post.class).list();
		return list;
	}

	public List<Post> findByZone(Long zid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select p.* from lt_post p where ";
		return null;
	}

	public List<Post> findBySection(Long sid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select p.* from lt_post p where p.section_id = '"+sid+"'";
		List<Post> postList = session.createSQLQuery(sql).addEntity(Post.class).list();
		return postList;
	}

	public Integer findCount(Long sid) {
		// TODO Auto-generated method stub
		String sql = "select p.id from lt_post p where p.section_id = '"+sid+"'";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}else{
			return 0;
		}
	}

	public List<Post> findByPage(int begin, int limit1, Long sid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select p.* from lt_post p where p.section_id = '"+sid+"' limit "+begin+", "+limit1+" ";
		/*Query q = session.createSQLQuery("select p.* from lt_post p where p.section_id = '"+sid+"'");
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		//q.
		List<Post> list = q.list();*/
		List<Post> list = session.createSQLQuery(sql).addEntity(Post.class).list();
		//List<Product> list = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Product.class)).list();
		return list;
	}

	public List<Post> findTopTwo() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select p.* from lt_post p limit 2";
		List<Post> list = session.createSQLQuery(sql).addEntity(Post.class).list();
		return list;
	}

	public Integer findCount() {
		// TODO Auto-generated method stub
		String sql = "select p.id from lt_post p";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<Post> findByPage(int begin, int limit1) {
		// TODO Auto-generated method stub
		Session session = getSession();
		SQLQuery q = session.createSQLQuery("select p.* from lt_post p order by p.id").addEntity(Post.class);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		
		List<Post> list = q.list();
		return list;
	}

	

	

	
}
