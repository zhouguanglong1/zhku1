package com.zhku.community.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.base.util.HqlHelper;
import com.zhku.base.util.PageBean1;
import com.zhku.base.util.SqlHelper;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.dao.ProductDao;
@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.getAll();
	}
	public Product findByPid(Long pid) {
		// TODO Auto-generated method stub
		return productDao.get(pid);
	}
	public PageBean1<Product> findByPage(Integer page) throws ParseException {
		int limit1 = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Product> pageBean = new PageBean1<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = productDao.findCount();
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
		
		List<Product> list = productDao.findByPage(begin, limit1);
		pageBean.setList(list);
		
		return pageBean;
	}
	public PageBean1<Product> findByCid(Long cid, Integer page) throws Exception {
		int limit1 = 9; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Product> pageBean = new PageBean1<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = productDao.findCountByCid(cid);
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
		
		List<Product> list = productDao.findByPage(begin, limit1, cid, page);
		pageBean.setList(list);
		
		return pageBean;
	}
	public PageBean1<Product> findByCsid(Long csid, Integer page) {
		int limit1 = 9; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Product> pageBean = new PageBean1<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = productDao.findCountByCsid(csid);
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
		
		List<Product> list = productDao.findByPageAndCsid(begin, limit1, csid, page);
		pageBean.setList(list);
		
		return pageBean;
	}
	public void deleteProduct(Long pid) {
		// TODO Auto-generated method stub
		//Product p = productDao.get(pid);
		productDao.delete(pid);
	}
	public void update(Product p) {
		// TODO Auto-generated method stub
		productDao.update(p);
	}
	public void save(Product p) {
		// TODO Auto-generated method stub
		productDao.save(p);
	}
	public List<Product> findByInput(String pname, String shop_price_from,
			String shop_price_to, String pdateStart, String pdateEnd,
			String address) {
		// TODO Auto-generated method stub
		return productDao.findByInput(pname,shop_price_from,shop_price_to,pdateStart,pdateEnd,address);
	}
	public PageBean1<Product> findByPname(String srchtxt, Integer page) {
		// TODO Auto-generated method stub
		int limit1 = 9; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Product> pageBean = new PageBean1<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = productDao.findCountByPname(srchtxt);
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
		
		List<Product> list = productDao.findByPage(begin, limit1, srchtxt, page);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	
	
	
}
