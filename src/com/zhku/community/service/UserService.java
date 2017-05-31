package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.community.bean.sys.User;
import com.zhku.community.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void save1(User user) {
		// TODO Auto-generated method stub
		userDao.save1(user);
	}

	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		/*String sql = "select * from sys_user where username = '"+username+"'";
		List<Object> list = userDao.findOneSQLQuery(sql);
		if(list.size() > 0 && list!=null){
			return list.get(0);
		}*/
		//return (User)list.get(0);
		return userDao.findByUserName(username);
		//userDao.findByUserName(username)
	}

	public boolean findByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_user where username = '"+username+"' and password = '"+password+"'";
		List<Object> list = userDao.findOneSQLQuery(sql);
		if(list.size() > 0 && list!=null){
			return true;
		}
		
		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		/*String sql = "select * from sys_user";
		List<Object> list = userDao.findOneSQLQuery(sql);
		List<User> userList = null;
		for (Object object : list) {
			User user = (User) object;
			userList.add(user);
		}*/
		
		//return getSession().createQuery(hql).list();
		return userDao.getAll();
	}

	public User getUser(Long uid) {
		// TODO Auto-generated method stub
		return userDao.get(uid);
	}

	public void delete(Long uid) {
		// TODO Auto-generated method stub
		userDao.delete(uid);
	}

	public void update(User u) {
		// TODO Auto-generated method stub
		userDao.update(u);
	}

	public void delete(User u) {
		// TODO Auto-generated method stub
		//userDao.delete(id);
	}

	public boolean validateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.validateAdmin(username,password);
	}

	public User getUserByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.getBy(username,password);
	}

	public List<User> findByQuery(String username, String status,
			String roleStatus) {
		// TODO Auto-generated method stub
		return userDao.findByQuery(username,status,roleStatus);
	}

	public List<Object[]> statUser() {
		// TODO Auto-generated method stub
		return userDao.statUser();
	}

}
