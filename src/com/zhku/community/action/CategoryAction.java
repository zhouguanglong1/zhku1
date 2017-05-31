package com.zhku.community.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhku.base.action.BaseAct;
import com.zhku.base.util.PageBean1;
import com.zhku.base.util.ResponseUtil;
import com.zhku.community.bean.shop.Category;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.service.CategoryService;
import com.zhku.community.service.ProductService;


@Controller
@RequestMapping("/category")
public class CategoryAction extends BaseAct {
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	ProductService productService;
	@RequestMapping(value="categoryList.action",method=RequestMethod.GET)
	public String categoryList(Integer page,Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//查询一级分类
		List<Category> categoryList = categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		
		//查询商品
		PageBean1<Product> pageBean = productService.findByPage(page);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		List<Product> list = pageBean.getList();
		/*for (Product product : list) {
			System.out.println("product---------------------"+product);
		}*/
		request.setAttribute("productList", pageBean.getList());
		
		return "/sys/zhkuShop";
	}
	
	
	
	
	@RequestMapping(value="adminCategoryList.action")
	public String adminCategoryList(Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//查询一级分类
		List<Category> categoryList = categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		
		return "/admin/product/categoryList1";
	}
	
	@RequestMapping(value="queryCategory.action")
	public String queryCategory(Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String cname = request.getParameter("cname"); 
		/*String strName = null;
		if(cname!=null&&!"".equals(cname)){
			strName = new String(cname.getBytes("iso-8859-1"),"UTF-8");
		}*/
         
		//查询一级分类
		List<Category> categoryList = categoryService.findByIdOrName(id,cname);
		request.getSession().setAttribute("categoryList", categoryList);
		
		return "/admin/product/categoryList1";
	}
	
	@RequestMapping(value="addCategory.action")
	public String addCategory(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String cname = request.getParameter("cname"); 
        //String strName = new String(cname.getBytes("iso-8859-1"),"UTF-8");
        Category c = new Category();
		c.setCname(cname);
        categoryService.addCategory(c);
        return "redirect:/category/adminCategoryList.action";
	}
	
	
	@RequestMapping("findCategory.action")
	public String findCategory(Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	
		
		Category c = categoryService.findCategory(cid);
		request.setAttribute("c", c);
		return "/admin/product/modifyCategory1";
	}
	
	@RequestMapping("modifyCategory.action")
	public String modifyCategory(Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	
		String cname = request.getParameter("cname"); 
        //String strName = new String(cname.getBytes("iso-8859-1"),"UTF-8");
        Category c = categoryService.findCategory(cid);
        c.setCname(cname);
        categoryService.modifyCategory(c);
		return "redirect:/category/adminCategoryList.action";
	}	
	
	@RequestMapping("deleteCategory.action")
	public String deleteCategory(Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jsonObject=new JSONObject();
		Map<String,Object> result = new HashMap<String, Object>();
		if(!categoryService.exist(cid)){
			categoryService.delete(cid);
			result.put("success", true);
		}else{
			result.put("success",false);
			//throw new Exception("该分类下还有二级分类，不能删除！");
		}
		jsonObject = JSONObject.fromObject(result);
		ResponseUtil.write(response, jsonObject);
		
		return "redirect:/category/adminCategoryList.action";
	}
}
