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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="lt_zone")
public class Zone {
//大版块类
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	//大版块名称
	@Column(length=50,nullable=false)
	private String name;		
	
	//描述
	@Column(length=200)
	private String description;	
	
	//大版块下的版面
	@OneToMany(mappedBy="zone",fetch=FetchType.EAGER)
	private Set<Section> sectionList=new HashSet<Section>();		

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	public Set<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Set<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Zone [id=" + id + ", name=" + name + ", description="
				+ description + ", sectionList=" + sectionList + "]";
	}
	
	
}
