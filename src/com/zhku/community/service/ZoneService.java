package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.community.bean.luntan.Zone;
import com.zhku.community.dao.ZoneDao;

@Service
public class ZoneService {

	@Autowired
	private ZoneDao zoneDao;

	public List<Zone> findAll() {
		// TODO Auto-generated method stub
		return zoneDao.getAll();
	}

	public void save(Zone z) {
		// TODO Auto-generated method stub
		zoneDao.save(z);
	}

	public Zone getZone(Long zid) {
		// TODO Auto-generated method stub
		return zoneDao.get(zid);
	}

	public void update(Zone z) {
		// TODO Auto-generated method stub
		zoneDao.update(z);
	}

	public void deleteZone(Long zid) {
		// TODO Auto-generated method stub
		zoneDao.delete(zid);
	}

	public List<Zone> findByCondition(Long zid, String name, String description) {
		// TODO Auto-generated method stub
		return zoneDao.findByCondition(zid,name,description);
	}

	public String getTodayCount() {
		// TODO Auto-generated method stub
		return zoneDao.getTodayCount();
	}

	public String getYestodayCount() {
		// TODO Auto-generated method stub
		return zoneDao.getYestodayCount();
	}

	public String getCount() {
		// TODO Auto-generated method stub
		return zoneDao.getCount();
	}

	public String getUserCount() {
		// TODO Auto-generated method stub
		return zoneDao.getUserCount();
	}

	public Zone findByZid(Long zid) {
		// TODO Auto-generated method stub
		return zoneDao.get(zid);
	}
}
