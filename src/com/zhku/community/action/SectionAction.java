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
import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.luntan.Post;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.bean.luntan.Zone;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.PostService;
import com.zhku.community.service.SectionService;
import com.zhku.community.service.UserService;
import com.zhku.community.service.ZoneService;

@Controller
@RequestMapping("/section")
public class SectionAction extends BaseAct {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private UserService userService; 
	@Autowired
	private PostService postService; 
	
	@RequestMapping("adminSectionList.action")
	public String adminSectionList(Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Zone> zoneList = zoneService.findAll();
		List<User> userList = userService.findAll();
		request.getSession().setAttribute("zoneList", zoneList);
		request.getSession().setAttribute("userList", userList);
		//List<Section> sectionList = sectionService.findAll();
		
		PageBean1<Section> pageBean = sectionService.findByPage(page);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		
		return "/admin/forum/sectionList";
	}
	
	@RequestMapping("deleteSection.action")
	public String deleteSection(Long sid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		sectionService.delete(sid);
		return "redirect:/zone/zoneIndex.action";
	}
	
	@RequestMapping("queryBeforeUpdate.action")
	public String queryBeforeUpdate(Long sid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Section section = sectionService.get(sid);
		List<Zone> zoneList = zoneService.findAll();
		request.setAttribute("section", section);
		request.setAttribute("zoneList", zoneList);
		return "/forum/addSection";
	}
	
	@RequestMapping("addOrSaveSection.action")
	public String addOrSaveSection(String remark,Long sid,Long zid,HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile photoPath) throws Exception{
		System.out.println("ksdkfjds");
		if(sid==null || "".equals(sid)){
			Section section = new Section();
			String username = (String) request.getSession().getAttribute("username");
			User master = userService.findByUserName(username);
			section.setMaster(master);
			section.setStatus("1");
			section.setRemark(remark);
			Zone zone = zoneService.getZone(zid);
			section.setZone(zone);
			if(photoPath != null && !"".equals(photoPath)){
				String path = "E:\\project\\temp\\";
				String originalFilename = photoPath.getOriginalFilename();
				String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File file1 = new File(path+newFileName);
				
				photoPath.transferTo(file1);
				//图片上传成功，设置user属性，写入数据库
				section.setSectionImgPath(newFileName);
			}else{
				section.setSectionImgPath("");
			}
			sectionService.save(section);
		}else{
			Section section = sectionService.get(sid);
			if(photoPath != null && !"".equals(photoPath)){
				String path = "E:\\project\\temp\\";
				String originalFilename = photoPath.getOriginalFilename();
				String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File file1 = new File(path+newFileName);
				
				photoPath.transferTo(file1);
				//图片上传成功，设置user属性，写入数据库
				section.setSectionImgPath(newFileName);
			}
			String username = (String) request.getSession().getAttribute("username");
			User master = userService.findByUserName(username);
			section.setMaster(master);
			section.setStatus("1");
			section.setRemark(remark);
			Zone zone = zoneService.getZone(zid);
			section.setZone(zone);
			sectionService.update(section);
		}
		
		return "redirect:/zone/findByZid.action?zid="+zid;
	}
	
	
	
	@RequestMapping("querySection.action")
	public String querySection(Long sid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String sectionName1 = request.getParameter("sectionName"); 
		String sectionName = null;
		if(sectionName1 != null && !"".equals(sectionName1)){
			sectionName = new String(sectionName1.getBytes("iso-8859-1"),"UTF-8");
		}
		
		String remark1 = request.getParameter("remark"); 
		String remark = null;
		if(remark1 != null && !"".equals(remark1)){
			remark = new String(remark1.getBytes("iso-8859-1"),"UTF-8");
		}
		
		List<Section> sectionList = sectionService.findByQuery(sid,sectionName,remark);
		request.setAttribute("sectionList", sectionList);
		
		return "/admin/forum/querySectionList";
	}
	
	@RequestMapping("sectionPostList.action")
	public String sectionPostList(Integer page,Long sid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		PageBean1<Post> pageBean = null;
		
		pageBean = postService.findByPage(page,sid);
		Section section = sectionService.get(sid);
		//List<Post> sectionPostList = postService.findBySection(sid);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("section", section);
		
		return "/forum/sectionPostList";
	}
	
}
