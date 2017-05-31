package com.zhku.community.bean.luntan;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.mysql.jdbc.Clob;
import com.zhku.community.bean.sys.User;

/**
 * 帖子表
 * @author zgl
 *
 */
@Entity
@Table(name="lt_Post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//帖子名称
	@Column(length = 100,nullable = false)
	private String postName;
	
	//帖子内容
	//@Column(length = 255,nullable = true)
	@Lob
	@Column(columnDefinition="TEXT")
	private String content;
	
	//发布时间
	@Column(length = 30,nullable = true)
	private Date publishTime;
	
	//修改时间
	@Column(length = 30,nullable = true)
	private Date modifyTime;
	
	//板块id
	@ManyToOne
	@JoinColumn(name="section_id")
	private Section section;
	
	//所属用户
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//点击量
	@Column(length = 5,nullable = true)
	private Long count;
	//是否精华贴，默认否
	@Column(length = 1,nullable = true)
	private int good=0;
	
	//是否置顶，默认否
	@Column(length = 1,nullable = true)
	private int top=0;
	
	//回复贴
	@OneToMany(mappedBy="post",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)  
    //@JoinColumn(name="post_id")  cascade=CascadeType.ALL,
	@OrderBy("id asc")
	private Set<ReplyPost> replyList=new HashSet<ReplyPost>();
	
	//帖子状态（1是启用，0是失效）
	@Column(length = 5,nullable = true)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Set<ReplyPost> getReplyList() {
		return replyList;
	}

	public void setReplyList(Set<ReplyPost> replyList) {
		this.replyList = replyList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", postName=" + postName + ", content="
				+ content + ", publishTime=" + publishTime + ", modifyTime="
				+ modifyTime + ", section=" + section + ", user=" + user
				+ ", count=" + count + ", good=" + good + ", top=" + top
				+ ", replyList=" + replyList + ", status=" + status + "]";
	}


	
	
}
