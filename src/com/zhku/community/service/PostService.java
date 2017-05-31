package com.zhku.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.dao.PostDao;

@Service
public class PostService {
	@Autowired
	private PostDao postDao; 

	public List<Post> findByUser(Long id) {
		// TODO Auto-generated method stub
		return postDao.findByUser(id);
	}

	public void save(Post post) {
		// TODO Auto-generated method stub
		postDao.save(post);
	}

	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return postDao.findAll();
	}

	public List<Post> findByZone(Long zid) {
		// TODO Auto-generated method stub
		return postDao.findByZone(zid);
	}

	public List<Post> findBySection(Long sid) {
		// TODO Auto-generated method stub
		return postDao.findBySection(sid);
	}

	public PageBean1<Post> findByPage(Integer page, Long sid) {
		// TODO Auto-generated method stub
		int limit1 = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Post> pageBean = new PageBean1<Post>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = postDao.findCount(sid);
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
		
		List<Post> list = postDao.findByPage(begin, limit1, sid);
		pageBean.setList(list);
		
		return pageBean;
	}

	public void delete(Long pid) {
		// TODO Auto-generated method stub
		postDao.delete(pid);
	}

	public Post get(Long pid) {
		// TODO Auto-generated method stub
		return postDao.get(pid);
	}

	public void update(Post post) {
		// TODO Auto-generated method stub
		postDao.update(post);
	}

	public List<Post> findTopTwo() {
		// TODO Auto-generated method stub
		return postDao.findTopTwo();
	}

	public PageBean1<Post> findByPage(Integer page) {
		int limit1 = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		//page = 1;
		PageBean1<Post> pageBean = new PageBean1<Post>();
		pageBean.setPage(page);
		pageBean.setLimit(limit1);
		// 查询总记录数:
		//String sql = "select count(*) from shop_product";
		Integer totalCount = postDao.findCount();
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
		
		List<Post> list = postDao.findByPage(begin, limit1);
		pageBean.setList(list);
		
		return pageBean;
	}


	
	
}
