package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.dao.SectionDao;

@Service
public class SectionService {

	@Autowired
	private SectionDao sectionDao;

	public List<Section> findAll() {
		// TODO Auto-generated method stub
		return sectionDao.getAll();
	}

	public PageBean1<Section> findByPage(Integer page) {
		int limit1 = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Section> pageBean = new PageBean1<Section>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = sectionDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 总页数的封装
		if(totalCount % limit1 == 0){
			totalPage = totalCount / limit1;
		}else{
			totalPage = totalCount / limit1 + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品数据集合:
		int begin = (page - 1) * limit1;
		
		List<Section> list = sectionDao.findByPage(begin, limit1);
		pageBean.setList(list);
		
		return pageBean;
	}

	public void delete(Long sid) {
		// TODO Auto-generated method stub
		sectionDao.delete(sid);
	}

	public List<Section> findByQuery(Long sid, String sectionName, String remark) {
		// TODO Auto-generated method stub
		return sectionDao.findByQuery(sid,sectionName,remark);
	}

	public Section get(Long id) {
		// TODO Auto-generated method stub
		return sectionDao.get(id);
	}

	public List<Section> findByZone(Long zid) {
		// TODO Auto-generated method stub
		return sectionDao.findByZone(zid);
	}

	public void save(Section section) {
		// TODO Auto-generated method stub
		sectionDao.save(section);
	}

	public void update(Section section) {
		// TODO Auto-generated method stub
		sectionDao.update(section);
	} 
}
