package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.community.bean.shop.Category;
import com.zhku.community.bean.shop.CategorySecond;
import com.zhku.community.dao.CategoryDao;
@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.getAll();
	}
	public List<Category> findByIdOrName(Long id, String cname) {
		// TODO Auto-generated method stub
		return categoryDao.findByIdOrName(id,cname);
	}
	public void addCategory(Category c) {
		// TODO Auto-generated method stub
		categoryDao.save(c);
	}
	public Category findCategory(Long cid) {
		// TODO Auto-generated method stub
		return categoryDao.get(cid);
	}
	public void modifyCategory(Category c) {
		// TODO Auto-generated method stub
		categoryDao.update(c);
	}
	public Category get(Long cid) {
		// TODO Auto-generated method stub
		return categoryDao.get(cid);
	}
	public boolean exist(Long cid) {
		// TODO Auto-generated method stub
		return categoryDao.exist(cid);
	}
	public void delete(Long cid) {
		// TODO Auto-generated method stub
		categoryDao.delete(cid);
	}
	
	
	
}
