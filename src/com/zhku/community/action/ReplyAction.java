package com.zhku.community.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import com.zhku.base.action.BaseAct;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.ReplyPost;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.PostService;
import com.zhku.community.service.ReplyService;
import com.zhku.community.service.UserService;

@Controller
@RequestMapping("/reply")
public class ReplyAction extends BaseAct {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
	@RequestMapping("saveReply.action")
	public String saveReply(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String pid1 = request.getParameter("reply.post.id");
		Long pid = Long.valueOf(pid1);
		Post post = postService.get(pid);
		String content1 = request.getParameter("reply.content");
		String content = null;
		if(content1!=null&&!"".equals(content1)){
			content = new String(content1.getBytes("iso-8859-1"),"utf-8");
		}
		String username = (String) request.getSession().getAttribute("username");
		User user = null;
		if(username!=null&&!"".equals(username)){
			user = userService.findByUserName(username);
		}
		ReplyPost replyPost = new ReplyPost();
		replyPost.setReplyTime(new Date());
		replyPost.setUser(user);
		replyPost.setContent(content);
		replyPost.setPost(post);
		replyService.save(replyPost);
		//System.out.println("content-----------"+content);
		return "redirect:/post/postDetail.action?pid="+pid;
	}
	
	@RequestMapping("deleteReply.action")
	public String deleteReply(Long replyId, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		//Reply reply=replyService.findReplyById(replyId);
		Long pid = replyService.findIdByRid(replyId);
		replyService.deleteReply(replyId);
		/*JSONObject result=new JSONObject();
		result.put("success", true);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(result.toString());
		out.flush();
		out.close();
		*/
		return "redirect:/post/postDetail.action?pid="+pid;
	}
	
}
