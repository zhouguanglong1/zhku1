package com.zhku.community.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.luntan.ReplyPost;
import com.zhku.community.bean.luntan.Section;

@Repository
public class ReplyDao extends BaseDaoImpl<ReplyPost, Long> {

	public List<ReplyPost> getByPid(Long pid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select r.* from lt_replypost r where r.post_id = '"+pid+"' order by r.replyTime";
		List<ReplyPost> replyList = session.createSQLQuery(sql).addEntity(ReplyPost.class).list();
		return replyList;
	}

	public Long findIdByRid(Long replyId) {
		// TODO Auto-generated method stub
		
		//Session session = getSession();
		String sql = "select r.post_id from lt_replypost r where r.id = '"+replyId+"'";
		List<Object> list = super.findOneSQLQuery(sql);
		Long pid = null;
		if(list!=null&&!"".equals(list)){
			pid = Long.valueOf(list.get(0).toString());
		}
		return pid;
	}

}
