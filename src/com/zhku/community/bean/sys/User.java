package com.zhku.community.bean.sys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.zhku.community.bean.infoshow.Scenery;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.ReplyPost;
import com.zhku.community.bean.luntan.Section;





/**
 * 用户表
 * @author zgl
 *
 */
@Entity
@Table(name="sys_User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50,nullable = false)
	private String username;
	@Column(length = 50,nullable = false)
	private String password;
	
	//真实姓名
	@Column(length = 20,nullable = true)
	private String name;
	//性别
	@Column(length = 5,nullable = true)
	private String sex;
	//邮箱
	@Column(length = 50,nullable = false)
	private String email;
	//专业
	@Column(length = 20,nullable = true)
	private String major;
	//手机
	@Column(length = 30,nullable = true)
	private String telephone;
	//班级
	@Column(length = 20,nullable = true)
	private String className;
	//头像图片路径
	@Column(length = 200,nullable = true)
	private String photoPath;
	//用户状态
	@Column(length = 5,nullable = true)
	private String status;
	//用户角色（1表示普通用户，2表示管理员）
	@Column(length = 5,nullable = true)
	private String roleStatus;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="master_id", updatable=false)
	private Set<Section> sectionList=new HashSet<Section>();
	
	@OneToMany(mappedBy="user")
	@Cascade(value={CascadeType.DELETE})
	private Set<Post> postList=new HashSet<Post>();
	
	@OneToMany(mappedBy="user")
	private Set<ReplyPost> replyList=new HashSet<ReplyPost>();
	
	@OneToMany(mappedBy="user")
	@Cascade(value={CascadeType.DELETE})
	private Set<Scenery> sceneryList=new HashSet<Scenery>();
	
	//添加时间
	@Column(length = 30,nullable = true)
	private Date addTime;
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	

	public Set<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(Set<Section> sectionList) {
		this.sectionList = sectionList;
	}
	public Set<Post> getPostList() {
		return postList;
	}
	public void setPostList(Set<Post> postList) {
		this.postList = postList;
	}
	public Set<ReplyPost> getReplyList() {
		return replyList;
	}
	public void setReplyList(Set<ReplyPost> replyList) {
		this.replyList = replyList;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", sex=" + sex + ", email="
				+ email + ", major=" + major + ", telephone=" + telephone
				+ ", className=" + className + ", photoPath=" + photoPath
				+ ", status=" + status + ", roleStatus=" + roleStatus + "]";
	}
	
}
	
	
	

