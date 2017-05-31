package com.zhku.community.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhku.base.action.BaseAct;
import com.zhku.community.bean.luntan.Section;
import com.zhku.community.bean.luntan.Zone;
import com.zhku.community.service.PostService;
import com.zhku.community.service.SectionService;
import com.zhku.community.service.ZoneService;


@Controller
@RequestMapping("/zone")
public class ZoneAction extends BaseAct {

	@Autowired
	private ZoneService zoneService;
	@Autowired
	private PostService postService;
	@Autowired
	private SectionService sectionService;  
	
	@RequestMapping("adminZoneList.action")
	public String zoneList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Zone> zoneList = zoneService.findAll();
		request.setAttribute("zoneList", zoneList);
 		return "/admin/forum/zoneList";
	}
	
	@RequestMapping("addOrSaveZone.action")
	public String addOrSaveZone(Long zid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String name = (String) request.getParameter("name");
		String description = (String) request.getParameter("description");
		
		if(zid == null || "".equals(zid)){
			Zone z = new Zone();
			z.setName(name);
			z.setDescription(description);
			zoneService.save(z);
		}else{
			Zone z = zoneService.getZone(zid);
			z.setName(name);
			z.setDescription(description);
			zoneService.update(z);
		}
		
		
		return "redirect:/zone/adminZoneList.action";
	}
	
	
	@RequestMapping("findZone.action")
	public String findZone(Long zid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Zone z = zoneService.getZone(zid);
		request.setAttribute("zone", z);
		return "/admin/forum/addZone";
	}
	
	@RequestMapping("deleteZone.action")
	public String deleteZone(Long zid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		zoneService.deleteZone(zid);
		return "redirect:/zone/adminZoneList.action";
	}
		
	
	
	@RequestMapping(value="queryZone.action")
	public String queryCategory(Long zid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String name1 = request.getParameter("name"); 
		String name = null;
		if(name1 != null && !"".equals(name1)){
			name = new String(name1.getBytes("iso-8859-1"),"UTF-8");
		}
		
		String description1 = request.getParameter("description"); 
		String description = null;
		if(description1 != null && !"".equals(description1)){
			description = new String(description1.getBytes("iso-8859-1"),"UTF-8");
		}
		//查询一级分类
		List<Zone> zoneList = zoneService.findByCondition(zid,name,description);
		request.getSession().setAttribute("zoneList", zoneList);

		return "/admin/forum/zoneList";
	}
	
	@RequestMapping("zoneIndex.action")
	public String zoneIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Zone> list = zoneService.findAll();
		String todayCount = zoneService.getTodayCount();
		String yestodayCount = zoneService.getYestodayCount();
		String allCount = zoneService.getCount();
		String userCount = zoneService.getUserCount();
		request.setAttribute("zoneList", list);
		request.getSession().setAttribute("todayCount", todayCount);
		request.getSession().setAttribute("yestodayCount", yestodayCount);
		request.getSession().setAttribute("allCount", allCount);
		request.getSession().setAttribute("userCount", userCount);
		return "/sys/board";
	}
	
	@RequestMapping("findByZid.action")
	public String findByZid(Long zid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Zone z = zoneService.findByZid(zid);
		
		List<Section> sectionList = sectionService.findByZone(zid);
		request.setAttribute("z", z);
		request.setAttribute("sectionList", sectionList);
		return "/forum/sectionList";
	}
		
}
