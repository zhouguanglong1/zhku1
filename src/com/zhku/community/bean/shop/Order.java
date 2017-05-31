package com.zhku.community.bean.shop;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zhku.community.bean.sys.User;


/**
 * 订单的实体
 * @author zgl
 *CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKC3DF62E5AA3D9C7` (`uid`),
  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name="shop_Order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 20,nullable = true)
	private Double total;
	@Column(length = 30,nullable = true)
	private Date ordertime;
	@Column(length = 5,nullable = true)
	private String state;
	@Column(length = 100,nullable = true)
	private String addr;
	@Column(length = 20,nullable = true)
	private String phone;
	@Column(length = 10,nullable = true)
	private String name;
			
	@Column(length = 20,nullable = true)
	private String orderNum;
	// 订单的所属的用户
	@ManyToOne
	@JoinColumn(name="User_ID")
	private User user;
	// 放的是订单项的集合.
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    @JoinColumn(name="Order_ID")
	private Set<OrderItem> orderItems;
	// = new HashSet<OrderItem>();
	
	
	public Double getTotal() {
		return total;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", ordertime="
				+ ordertime + ", state=" + state + ", addr=" + addr
				+ ", phone=" + phone + ", name=" + name + ", orderNum="
				+ orderNum + ", user=" + user + ", orderItems=" + orderItems
				+ "]";
	}

	
	
}
