package com.zhku.community.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.shop.Order;
import com.zhku.community.bean.sys.User;

@Repository
public class OrderDao extends BaseDaoImpl<Order, Long> {

	public Integer findCount() {
		// TODO Auto-generated method stub
		String sql = "select o.id from shop_Order o";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}else{
			return 0;
		}
		
	}

	public List<Order> findByPage(int begin, int limit1) {
		Session session = getSession();
		Query q = session.createQuery("select o from Order as o order by o.ordertime desc");
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		List<Order> list = q.list();
		//List<Product> list = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Product.class)).list();
		return list;
	}

	public List<Order> getOrderByUser(User user) {
		// TODO Auto-generated method stub
		Session session = getSession();
		SQLQuery q = session.createSQLQuery("select * from shop_order o where o.user_id = '"+user.getId()+"'").addEntity(Order.class);
		List<Order> list = q.list();
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}

}
