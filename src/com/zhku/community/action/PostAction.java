package com.zhku.community.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhku.base.action.BaseAct;
import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.ReplyPost;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.PostService;
import com.zhku.community.service.ReplyService;
import com.zhku.community.service.SectionService;
import com.zhku.community.service.UserService;

@Controller
@RequestMapping("/post")
public class PostAction extends BaseAct{

	@Autowired
	private PostService postService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserService userService; 
	@Autowired
	private ReplyService replyService; 
	
	@RequestMapping("postListByUser.action")
	public String postListByUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = (String) request.getSession().getAttribute("username");
		User user = userService.findByUserName(username);
		
		List<Post> postList = postService.findByUser(user.getId());
		
		request.setAttribute("postList", postList);
		return "/forum/postList";
	}
	
	//adminPostList.action
	@RequestMapping("adminPostList.action")
	public String adminPostList(Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		PageBean1<Post> pageBean = postService.findByPage(page);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
	
		return "/admin/forum/postList";
	}
	@RequestMapping("postList.action")
	public String postList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Post> allPostList = postService.findAll();
		
		request.setAttribute("allPostList", allPostList);
		return "/forum/allPostList";
	}
	
	@RequestMapping("deletePost.action")
	public String deletePost(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		postService.delete(pid);
		
		return "redirect:/post/postListByUser.action";
	}
	
	
	@RequestMapping(value="QueryBeforeAdd.action")
	public String QueryBeforeAdd(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Section> list = sectionService.findAll();
		request.setAttribute("sectionList", list);
		return "/forum/addPost";
	}
	
	@RequestMapping(value="queryBeforeUpdate.action")
	public String queryBeforeUpdate(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Post post = postService.get(pid);
		List<Section> list = sectionService.findAll();
		request.setAttribute("post", post);
		request.setAttribute("sectionList", list);
		return "/forum/addPost";
	}
	
	@RequestMapping("postDetail.action")
	public String postDetail(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Post post = postService.get(pid);
		request.setAttribute("post", post);
		List<ReplyPost> replyList = replyService.getByPid(pid);
		request.setAttribute("replyList", replyList);
		return "/forum/postDetail";
	}
	
	
	@RequestMapping("saveOrUpdate.action")
	public String save(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = request.getParameter("username"); 
		String sectionId = request.getParameter("sectionid");
		String postName = request.getParameter("postName");
		String content = request.getParameter("content");
		User user = userService.findByUserName(username);
		Section section = sectionService.get(Long.valueOf(sectionId));
		if(pid==null || "".equals(pid)){
			Post post = new Post();
			post.setPostName(postName==null?"":postName);
			post.setContent(content==null?"":content);
			post.setSection(section);
			post.setUser(user);
			post.setPublishTime(new Date());
			post.setStatus("1");
			
			postService.save(post);
			return "redirect:/post/postListByUser.action";
		}else{
			Post post = postService.get(pid);
			post.setContent(content==null?"":content);
			post.setPostName(postName==null?"":postName);
			post.setSection(section);
			post.setUser(user);
			post.setModifyTime(new Date());
			postService.update(post);
			return "redirect:/section/sectionPostList.action?page=1&sid="+section.getId();
		}
		
		
	}
}
