package com.zhku.community.bean.shop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车对象:
 * @author zgl
 *
 */
public class Cart {
	// 购物车存放多个购物项:
	// Map集合用商品的ID作为Map的key , 购物项作为Map的value
	private Map<Long,CartItem> map = new HashMap<Long,CartItem>();
	
	// 提供获得map的value的集合:
	// 相当于有一个属性:cartItems
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	// 总计:
	private Double total = 0d;
	
	public Double getTotal() {
		return total;
	}

	// 提供三个方法:
	// 将购物项添加到购物车:
	public void addCart(CartItem cartItem){
		// 获得购物项标识id
		Long pid = cartItem.getProduct().getId();
		if(map.containsKey(pid)){
			// 购物车中已经有购物项
			// 购物车中已经有的购物项信息
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			// 购物车中不存在该购物项
			map.put(pid, cartItem);
		}
		// 总计:
		total += cartItem.getSubtotal();
	}
	
	// 将购物项从购物车中移除:
	public void removeCart(Integer pid){
		// 将购物项从map集合中移除:
		CartItem cartItem = map.remove(pid);
		// 设置总计钱数 
		total -= cartItem.getSubtotal();
	}
	
	// 清空购物车:
	public void clearCart(){
		// 清空map
		map.clear();
		// 总计设置为0
		total = 0d;
	}
}
