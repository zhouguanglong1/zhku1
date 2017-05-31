package com.zhku.community.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zhku.base.dao.impl.BaseDaoImpl;
import com.zhku.community.bean.luntan.Zone;

@Repository
public class ZoneDao extends BaseDaoImpl<Zone, Long> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public List<Zone> findByCondition(Long zid, String name, String description) {
		// TODO Auto-generated method stub
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT z.* FROM lt_zone z where 1=1 ");
		if(zid!=null && !"".equals(zid)){
			sb.append("and z.id = '"+zid+"'");
		}
		if(name!=null && !"".equals(name)){
			sb.append("and z.name like '%"+name+"%'");
		}
		if(description!=null && !"".equals(description)){
			sb.append("and z.description like '%"+description+"%'");
		}
		sb.append(" order by z.id asc");
		@SuppressWarnings("unchecked")
		List<Zone> zonelist = session.createSQLQuery(sb.toString()).addEntity(Zone.class).list();
		return zonelist;
	}

	public String getTodayCount() {
		// TODO Auto-generated method stub
		
		String today = dateFormat.format(new Date());
		//Session session = getSession();
		String sql = "select count(*) from lt_post p where substring(p.publishTime,1,10) = '"+today+"' and p.status = 1";
		//Object count = super.createSQLQuery(sql, today);
		List<Object> obj = super.findOneSQLQuery(sql);
		if(obj!=null&&obj.size()>0){
			return obj.get(0).toString();
			//return list.get(0).intValue();
		}
		return null;
	}

	public String getYestodayCount() {
		// TODO Auto-generated method stub
		//Session session = getSession();
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day-1);
		String yestoday = dateFormat.format(c.getTime());
		String sql = "select count(*) from lt_post p where substring(p.publishTime,1,10) = '"+yestoday+"' and p.status = 1";
		List<Object> obj = super.findOneSQLQuery(sql);
		if(obj!=null&&obj.size()>0){
			return obj.get(0).toString();
			//return list.get(0).intValue();
		}
		return null;
	}

	public String getCount() {
		// TODO Auto-generated method stub
		//Session session = getSession();
		String sql = "select count(*) from lt_post where status = 1";
		List<Object> obj = super.findOneSQLQuery(sql);
		if(obj!=null&&obj.size()>0){
			return obj.get(0).toString();
			//return list.get(0).intValue();
		}
		return null;
	}

	public String getUserCount() {
		// TODO Auto-generated method stub
		//Session session = getSession();
		String sql = "select count(*) from sys_user u where u.roleStatus = 1";
		List<Object> obj = super.findOneSQLQuery(sql);
		if(obj!=null&&obj.size()>0){
			return obj.get(0).toString();
			//return list.get(0).intValue();
		}
		return null;
	}

	
	
}
