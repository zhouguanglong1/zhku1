package com.zhku.community.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhku.base.action.BaseAct;
import com.zhku.community.bean.infoshow.Scenery;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.SceneryService;
import com.zhku.community.service.UserService;

@Controller
@RequestMapping("/scenery")
public class SceneryAction extends BaseAct {

	@Autowired
	private SceneryService sceneryService;
	@Autowired
	private UserService userService; 
	
	@RequestMapping("addScenery.action")
	public String addScenery(String sname,String remark,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="file") MultipartFile file) throws Exception{
		
		Scenery s = new Scenery();
		s.setSname(sname==null?"":sname);
		if(file != null && !"".equals(file)){
			String path = "E:\\project\\temp\\";
			String originalFilename = file.getOriginalFilename();
			String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
			File file1 = new File(path+newFileName);
			
			file.transferTo(file1);
			//图片上传成功，设置user属性，写入数据库
			s.setPhotoPath(newFileName);
		}else{
			s.setPhotoPath("");
		}
		s.setRemark(remark==null?"":remark);
		String username = (String) request.getSession().getAttribute("username");
		User user = userService.findByUserName(username);
		if(user!=null){
			s.setUser(user);
		}
		sceneryService.save(s);
		return "redirect:/scenery/sceneryList.action";
		
	}
	
	
	@RequestMapping("sceneryList.action")
	public String sceneryList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Scenery> sceneryList = sceneryService.findAll();
		request.getSession().setAttribute("sceneryList", sceneryList);
		return "/sys/zhkuStyle";
		
	}
	
	
}
