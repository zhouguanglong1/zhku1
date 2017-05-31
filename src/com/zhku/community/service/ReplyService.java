package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.community.bean.luntan.ReplyPost;
import com.zhku.community.dao.ReplyDao;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyDao replyDao;

	public List<ReplyPost> getByPid(Long pid) {
		// TODO Auto-generated method stub
		return replyDao.getByPid(pid);
	}

	public void save(ReplyPost replyPost) {
		// TODO Auto-generated method stub
		replyDao.save(replyPost);
	}

	public void deleteReply(Long rid) {
		// TODO Auto-generated method stub
		replyDao.delete(rid);
	}

	public Long findIdByRid(Long replyId) {
		// TODO Auto-generated method stub
		return replyDao.findIdByRid(replyId);
	}

}
