package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.shop.CategorySecond;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.dao.CategorySecondDao;

@Service
public class CategorySecondService {

	@Autowired
	CategorySecondDao categorySecondDao;
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.getAll();
	}
	public PageBean1<CategorySecond> findByPage(Integer page) {
		int limit1 = 9; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<CategorySecond> pageBean = new PageBean1<CategorySecond>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 总页数的封装
		if(totalCount % limit1 == 0){
			totalPage = totalCount / limit1;
		}else{
			totalPage = totalCount / limit1 + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品数据集合:
		int begin = (page - 1) * limit1;
		
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit1);
		pageBean.setList(list);
		
		return pageBean;
	}
	public CategorySecond findCategory(Long csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.get(csid);
	}
	public void modifyCategory(CategorySecond cs) {
		// TODO Auto-generated method stub
		categorySecondDao.update(cs);
	}
	public void save(CategorySecond cs) {
		// TODO Auto-generated method stub
		categorySecondDao.save(cs);
	}
	public CategorySecond find(Long csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.get(csid);
	}
	public boolean exist(Long csid) {
		// TODO Auto-generated method stub
		
		
		return categorySecondDao.exit(csid);
	}
	public void delete(CategorySecond cs) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(cs);
	}
	public List<CategorySecond> findByIdOrName(Long id, String strName) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByIdOrName(id,strName);
	}
	
}
