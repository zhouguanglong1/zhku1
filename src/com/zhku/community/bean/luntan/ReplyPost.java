package com.zhku.community.bean.luntan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.engine.Cascade;

import com.zhku.community.bean.sys.User;


/**
 * 回复帖子信息表
 * @author Administrator
 *
 */
@Entity
@Table(name="lt_ReplyPost")
public class ReplyPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//回复名
	@Column(length = 100,nullable = true)
	private String replyName;
	
	//内容
	@Lob
	@Column(columnDefinition="TEXT")
	private String content;
	
	//回复时间
	@Column(length = 30,nullable = true)
	private Date replyTime;
	
	//修改时间
	@Column(length = 30,nullable = true)
	private Date modifyTime;
	
	//所属帖子
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
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

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ReplyPost [id=" + id + ", replyName=" + replyName
				+ ", content=" + content + ", replyTime=" + replyTime
				+ ", modifyTime=" + modifyTime + ", post=" + post + ", user="
				+ user + "]";
	}

	
	
	
}
