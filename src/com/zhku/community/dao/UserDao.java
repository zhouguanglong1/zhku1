package com.zhku.community.dao;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.bean.sys.User;

@Repository
public class UserDao extends BaseDaoImpl<User, Long>{

	
	
	public void save1(User user) {
		super.save(user);
		
		//this.getHibernateTemplate().save(user);
	}


	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_user where username = '"+username+"'";
		Session session = getSession();
		
		SQLQuery q = session.createSQLQuery(sql).addEntity(User.class);
		//Assert.notNull(username, "username is required");
		//return (User) getSession().get(User.class, username);
		//load(User.class, username)
		List<User> list = q.list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public boolean validateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_user where username = '"+username+"' and password = '"+password+"' and roleStatus = 2";
		Session session = getSession();
		SQLQuery q = session.createSQLQuery(sql).addEntity(User.class);
		List<User> list = q.list();
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}


	public User getBy(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_user where username = '"+username+"' and password = '"+password+"' and roleStatus = 2";
		Session session = getSession();
		SQLQuery q = session.createSQLQuery(sql).addEntity(User.class);
		List<User> list = q.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public List<User> findByQuery(String username, String status,
			String roleStatus) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.* FROM sys_user u where 1=1 ");
		if(username!=null&&!"".equals(username)){
			sb.append(" and u.username like '%"+username+"%'");
		}
		if(status!=null&&!"-1".equals(status)){
			sb.append(" and u.status = '"+status+"'");
		}
		if(roleStatus!=null&& !"-1".equals(roleStatus)){
			sb.append(" and u.roleStatus = '"+roleStatus+"'");
		}
		List<User> list = session.createSQLQuery(sb.toString()).addEntity(User.class).list();
		return list;
	}


	public List<Object[]> statUser() {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "SELECT u.roleStatus,COUNT(*) FROM sys_user u GROUP BY u.roleStatus;";
		List<Object[]> list = session.createSQLQuery(sql).list();
		
		return list;
	}
	
	/*public List<User> findAll(){
		
		String sql = "select * from sys_user";
		Session session = getSession();
		List<User> list = getSession().createQuery(sql).list();
		return list;
	}*/

}
