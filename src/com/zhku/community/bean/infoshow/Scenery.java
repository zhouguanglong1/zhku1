package com.zhku.community.bean.infoshow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zhku.community.bean.sys.User;

@Entity
@Table(name="zk_scenery")
public class Scenery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 20,nullable = false)
	private String sname;
	@Column(length = 100,nullable = false)
	private String photoPath;
	@Column(length = 200,nullable = false)
	private String remark;
	//所属用户
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Scenery [id=" + id + ", sname=" + sname + ", photoPath="
				+ photoPath + ", remark=" + remark + "]";
	}
	
}
