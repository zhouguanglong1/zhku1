package com.zhku.community.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhku.base.action.BaseAct;
import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.shop.Order;
import com.zhku.community.bean.shop.OrderItem;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.OrderService;
import com.zhku.community.service.ProductService;
import com.zhku.community.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderAction extends BaseAct {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="submitOrder.action")
	public String submitOrder(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNum = formatter.format(new Date());
		Product product = productService.findByPid(pid);
		Order order = new Order();
		order.setOrdertime(new Date());
		order.setState("1"); // 1 未付款   2 已经付款.  3.已经发货   4 已经收货.
		order.setOrderNum(orderNum);
		order.setTotal(product.getShop_price());
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setCount(1);
		orderItem.setSubtotal(product.getShop_price());
		
		orderItem.setOrder(order);
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		orderItems.add(orderItem);
		order.setOrderItems(orderItems);
		//order.getOrderItems().add(orderItem);
		//设置订单所属用户
		String username = (String) request.getSession().getAttribute("username");
		User user = userService.findByUserName(username);
		order.setUser(user);
		
		orderService.save(order);
		request.setAttribute("order", order);
		
		return "/sys/order";
	}
	
	@RequestMapping(value="adminOrderList.action")
	public String adminOrderList(Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		PageBean1<Order> pageBean = null;
		try{
			 pageBean = orderService.findByPage(page);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//System.out.println("pageBean-------------"+pageBean);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		
		return "/admin/product/orderList";
	}
		
	@RequestMapping(value="payOrder.action")	
	public String payOrder(Long oid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Order o = orderService.getOrder(oid);
		String addr = request.getParameter("addr");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		o.setAddr(addr);
		o.setName(name);
		o.setPhone(phone);
		
		o.setState("2");//已付款
		
		orderService.update(o);
		return "/admin/product/paySuccess";
	}
	
	@RequestMapping("sendProduct.action")
	public String sendProduct(Long oid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Order o = orderService.getOrder(oid);
		o.setState("3");
		Set<OrderItem> orderItems = (Set<OrderItem>) o.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			Product p = orderItem.getProduct();
			p.setStatus(0);
			productService.update(p);
		}
		orderService.update(o);
		
		return "redirect:/order/adminOrderList.action?page=1";
	}
	
	@RequestMapping("myOrder.action")
	public String myOrder(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String username = (String) request.getSession().getAttribute("username");
		User user = (User) userService.findByUserName(username);
		List<Order> list = orderService.getOrderByUser(user);
		request.setAttribute("orderList", list);
		return "/sys/myOrder";
	}
	
	
	@RequestMapping("sureOrder.action")
	public String sureOrder(Long oid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Order o = orderService.getOrder(oid);
		o.setState("4");
		orderService.update(o);
		return "redirect:/order/myOrder.action";
	}
	
	@RequestMapping("deleteOrder.action")
	public String deleteOrder(Long oid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//Order o = orderService.getOrder(oid);
		orderService.delete(oid);
		return "redirect:/order/adminOrderList.action?page=1";
	}
}
