package com.zhku.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhku.base.action.BaseAct;
import com.zhku.community.bean.shop.Cart;
import com.zhku.community.bean.shop.CartItem;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.service.ProductService;


@Controller
@RequestMapping("/cart")
public class CartAction extends BaseAct {

	@Autowired
	ProductService productService; 
	
	@RequestMapping(value="addCart.action")
	public String addCart(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Product product = productService.findByPid(pid);
		
		CartItem cartItem = new CartItem();
		cartItem.setCount(1);
		cartItem.setProduct(product);
		Cart cart = getCart(request);
		cart.addCart(cartItem);
		
		request.setAttribute("product", product);
		return "/sys/cartList";
	}
	
	/**
	 * 从session范围获得购物车的代码
	 */
	public Cart getCart(HttpServletRequest request) {
		// 从session的范围获得Cart对象.
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断:
		if (cart == null) {
			// 创建购物车对象
			cart = new Cart();
			// 将购物车对象放入到session范围:
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
}
