package com.zhku.community.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhku.base.action.BaseAct;
import com.zhku.base.util.PageBean1;
import com.zhku.community.bean.shop.Category;
import com.zhku.community.bean.shop.CategorySecond;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.service.CategorySecondService;
import com.zhku.community.service.CategoryService;

@Controller
@RequestMapping("/categorySecond")
public class CategorySecondAction extends BaseAct {

	@Autowired
	CategorySecondService categorySecondService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("adminCategorySecondList.action")
	public String adminCategorySecondList(Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		List<Category> categoryList = categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		
		//查询商品
		PageBean1<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		
		
		return "/admin/product/categorySecondList1";
	}
	
	@RequestMapping("findSecondCategory.action")
	public String findSecondCategory(Long csid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CategorySecond cs = categorySecondService.find(csid);
		request.setAttribute("cs", cs);
		return "/admin/product/modifyCategorySecond1";
	}
	
	
	@RequestMapping("deleteSecondCategory.action")
	public String deleteSecondCategory(Long csid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CategorySecond cs = categorySecondService.find(csid);
		if(!categorySecondService.exist(csid)){
			categorySecondService.delete(cs);
		}else{
			throw new Exception("该分类下还有商品，不能删除");
		}
		return "redirect:/categorySecond/adminCategorySecondList.action?page=1";
	}
	
	@RequestMapping("modifyCategorySecond.action")
	public String modifyCategorySecond(Long csid,Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String csname = request.getParameter("csname"); 
        //String strName = new String(csname.getBytes("iso-8859-1"),"UTF-8");
        CategorySecond cs = categorySecondService.findCategory(csid);
        Category c = categoryService.get(cid);
        cs.setCsname(csname);
        cs.setCategory(c);
        categorySecondService.modifyCategory(cs);
		return "redirect:/categorySecond/adminCategorySecondList.action?page=1";
	}
	
	@RequestMapping("addCategorySecond.action")
	public String addCategorySecond(Long csid,Long cid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String csname = request.getParameter("csname"); 
        //String strName = new String(csname.getBytes("iso-8859-1"),"UTF-8");
        Category c = categoryService.get(cid);
        CategorySecond cs = new CategorySecond();
        cs.setCategory(c);
        cs.setCsname(csname);
        categorySecondService.save(cs);
		return "redirect:/categorySecond/adminCategorySecondList.action?page=1";
		
	}
	
	@RequestMapping(value="queryCategorySecond.action")
	public String queryCategorySecond(Long id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String csname = request.getParameter("csname");
		/*String strName = null;
		if(csname!=null&&!"".equals(csname)){
			strName = new String(csname.getBytes("iso-8859-1"),"UTF-8");
		}*/
        
		//查询二级分类
		List<CategorySecond> categorySecondList = categorySecondService.findByIdOrName(id,csname);
		request.getSession().setAttribute("categorySecondList", categorySecondList);
		
		return "/admin/product/queryCategorySecondList1";
	}
	
}
