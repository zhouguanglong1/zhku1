package com.zhku.community.bean.luntan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.zhku.community.bean.sys.User;

/**
 * 板块表
 * @author zgl
 *
 */
@Entity
@Table(name="lt_Section")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//板块名
	@Column(length = 50,nullable = false)
	private String sectionName;
	
	//版主
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="master_id",nullable=true)
	//@Column(length = 5)
	private User master;
	
	//所属大版块
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="zone_id",nullable=true)
	//@Column(length = 5)
	private Zone zone;
	
	//版块下的帖子
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    //@JoinColumn(name="section_id")
	@OrderBy("id asc")
	private Set<Post> postList=new HashSet<Post>();
	
	
	//板块描述
	@Column(length = 255,nullable = true)
	private String remark;
	
	//板块图片路径
	@Column(length = 100,nullable = true)
	private String sectionImgPath;
	
	//̬板块状态
	@Column(length = 5,nullable = true)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	
	public User getMaster() {
		return master;
	}

	public void setMaster(User master) {
		this.master = master;
	}

	
	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	

	public Set<Post> getPostList() {
		return postList;
	}

	public void setPostList(Set<Post> postList) {
		this.postList = postList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSectionImgPath() {
		return sectionImgPath;
	}

	public void setSectionImgPath(String sectionImgPath) {
		this.sectionImgPath = sectionImgPath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", sectionName=" + sectionName
				+ ", master=" + master + ", zone=" + zone + ", postList="
				+ postList + ", remark=" + remark + ", sectionImgPath="
				+ sectionImgPath + ", status=" + status + "]";
	}

	


	
}
