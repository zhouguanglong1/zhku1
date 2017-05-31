package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.shop.Order;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.bean.sys.User;
import com.zhku.community.dao.OrderDao;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao; 

	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	public PageBean1<Order> findByPage(Integer page) {
		// TODO Auto-generated method stub
		int limit1 = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Order> pageBean = new PageBean1<Order>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 总页数的封装
		if(totalCount % limit1 == 0){
			totalPage = totalCount / limit1;
		}else{
			totalPage = totalCount / limit1 + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 订单数据集合:
		int begin = (page - 1) * limit1;
		
		List<Order> list = orderDao.findByPage(begin, limit1);
		pageBean.setList(list);
		
		return pageBean;
	}

	public Order getOrder(Long oid) {
		// TODO Auto-generated method stub
		return orderDao.get(oid);
	}

	public void update(Order o) {
		// TODO Auto-generated method stub
		orderDao.update(o);
	}

	public List<Order> getOrderByUser(User user) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByUser(user);
	}

	public void delete(Long oid) {
		// TODO Auto-generated method stub
		orderDao.delete(oid);
	}

}
