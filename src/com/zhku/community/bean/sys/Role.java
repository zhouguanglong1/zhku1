package com.zhku.community.bean.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ��ɫ��
 * @author zgl
 *
 */
@Entity
@Table(name="lt_Role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//角色名称
	@Column(length = 20,nullable = false)
	private String roleName;
	
	//角色描述
	@Column(length = 100,nullable = true)
	private String remark;

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", remark="
				+ remark + "]";
	}

	
	
}
