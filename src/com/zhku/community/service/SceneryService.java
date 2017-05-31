package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.community.bean.infoshow.Scenery;
import com.zhku.community.dao.SceneryDao;

@Service
public class SceneryService {

	@Autowired
	private SceneryDao sceneryDao;

	public void save(Scenery s) {
		// TODO Auto-generated method stub
		sceneryDao.save(s);
	}

	public List<Scenery> findAll() {
		// TODO Auto-generated method stub
		return sceneryDao.getAll();
	}
}
