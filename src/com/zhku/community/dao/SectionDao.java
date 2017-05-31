package com.zhku.community.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.luntan.Section;

@Repository
public class SectionDao extends BaseDaoImpl<Section, Long> {

	public Integer findCount() {
		String sql = "select s.id from lt_section s";
		List<Object> list = this.findOneSQLQuery(sql);
		if(list!=null&&list.size()>0){
			return Integer.valueOf(list.size());
		}
		return 0;
	}

	public List<Section> findByPage(int begin, int limit1) {
		// TODO Auto-generated method stub
		Session session = getSession();
		SQLQuery q = session.createSQLQuery("select s.* from lt_section s order by s.id").addEntity(Section.class);
		q.setFirstResult(begin);
		q.setMaxResults(limit1);
		
		List<Section> list = q.list();
		return list;
	}

	public List<Section> findByQuery(Long sid, String sectionName, String remark) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select s.* from lt_section s where 1=1 ");
		if(sid!=null&&!"".equals(sid)){
			sb.append(" and s.id = '"+sid+"'");
		}
		if(sectionName!=null&&!"".equals(sectionName)){
			sb.append(" and s.sectionName like '%"+sectionName+"%'");
		}
		if(remark!=null&&!"".equals(remark)){
			sb.append(" and s.remark like '%"+remark+"%'");
		}
		List<Section> sectionList = session.createSQLQuery(sb.toString()).addEntity(Section.class).list();
		
		if(sectionList!=null&&sectionList.size()>0){
			return sectionList;
		}
		return null;
	}

	public List<Section> findByZone(Long zid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sql = "select s.* from lt_section s where s.zone_id = '"+zid+"'";
		List<Section> sectionList = session.createSQLQuery(sql).addEntity(Section.class).list();
		return sectionList;
	}

}
