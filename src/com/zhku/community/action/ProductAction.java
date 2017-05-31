package com.zhku.community.action;

import java.io.File;
import java.util.Date;
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
import com.zhku.community.bean.shop.Category;
import com.zhku.community.bean.shop.CategorySecond;
import com.zhku.community.bean.shop.Product;
import com.zhku.community.service.CategorySecondService;
import com.zhku.community.service.CategoryService;
import com.zhku.community.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductAction extends BaseAct {
	
	@Autowired
	ProductService productService; 
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	CategorySecondService categorySecondService; 
	
	@RequestMapping("productList.action")
	public String productList(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//查询一级分类
		List<Category> categoryList = categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		//String sql = "select * from sys_user";
		
		PageBean1<Product> pageBean = productService.findByPage(page);
		//System.out.println("pageBean-------------"+pageBean);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		
		
		return "/admin/user/userList";
	}
	
	
	@RequestMapping("findByPid.action")
	public String findByPid(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		Product product = productService.findByPid(pid);
		request.setAttribute("product", product);
		return "/sys/productDetail";
	}
	
	
	
	@RequestMapping("findByPname.action")
	public String findByPname(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*String page = request.getParameter("page");
		Integer page1 = null;
		if(page!=null&&!"".equals(page)){
			page1 = Integer.valueOf(page);
		}*/
		String srchtxt1 = request.getParameter("srchtxt");
		String srchtxt = null;
		if(srchtxt1!=null && !"".equals(srchtxt1)){
			srchtxt = new String(srchtxt1.getBytes("iso-8859-1"),"UTF-8");
		}
		PageBean1<Product> pageBean = productService.findByPname(srchtxt,1);
		//request.setAttribute("product", product);
		//request.setAttribute("categoryId", cid);
		request.setAttribute("pageBean1", pageBean);
		List<Product> list = pageBean.getList();
		/*for (Product product : list) {
			System.out.println("product---------------"+product);
		}*/
		request.setAttribute("productList1", pageBean.getList());
		return "/sys/productByPname";
	}
	
	@RequestMapping("findByCid.action")
	public String findByCid(Long cid,Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		PageBean1<Product> pageBean = productService.findByCid(cid,page);
		//request.setAttribute("product", product);
		request.setAttribute("categoryId", cid);
		request.setAttribute("pageBean1", pageBean);
		List<Product> list = pageBean.getList();
		/*for (Product product : list) {
			System.out.println("product---------------"+product);
		}*/
		request.setAttribute("productList1", pageBean.getList());
		return "/sys/productByCid";
	}
	
	@RequestMapping("findByCsid.action")
	public String findByCsid(Long csid,Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		PageBean1<Product> pageBean = productService.findByCsid(csid, page);
		request.setAttribute("categorySecondId", csid);
		request.setAttribute("pageBean2", pageBean);
		List<Product> list = pageBean.getList();
		/*for (Product product : list) {
			System.out.println("product---------------"+product);
		}*/
		request.setAttribute("productList2", pageBean.getList());
		return "/sys/productByCsid";
	}
	
	
	
	@RequestMapping("adminProductList.action")
	public String adminProductList(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//查询一级分类
		List<CategorySecond> categorySecondList = categorySecondService.findAll();
		request.getSession().setAttribute("categorySecondList", categorySecondList);
		//String sql = "select * from sys_user";
		PageBean1<Product> pageBean = null;
		try{
			 pageBean = productService.findByPage(page);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//System.out.println("pageBean-------------"+pageBean);
		//List<Product> productList = productService.findAll();
		request.setAttribute("pageBean", pageBean);
		
		
		return "/admin/product/productList1";
	}
	@RequestMapping("modifyProduct.action")
	public String modifyProduct(Long pid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Product p = productService.findByPid(pid);
		request.setAttribute("product", p);
		return "/admin/product/addProduct1";
	}

	@RequestMapping("deleteProduct.action")
	public String deleteProduct(Long pid,Integer page,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		productService.deleteProduct(pid);
		//request.getRequestDispatcher(path);
		return "redirect:/product/adminProductList.action?page="+page+"";
	}
	
	@RequestMapping("addOrSaveProduct.action")
	public String addOrSaveProduct(Product bean,Long csid,HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile file) throws Exception{
		
		if(bean.getId()==null || "".equals(bean.getId())){
			Product p = new Product();
			p.setAddress(bean.getAddress()==null?"":bean.getAddress());
			if(file != null && !"".equals(file)){
				String path = "E:\\project\\temp\\";
				String originalFilename = file.getOriginalFilename();
				String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File file1 = new File(path+newFileName);
				
				file.transferTo(file1);
				//图片上传成功，设置user属性，写入数据库
				p.setImage(newFileName);
			}else{
				p.setImage("");
			}
			p.setIs_hot(bean.getIs_hot()==null?"":bean.getIs_hot());
			p.setMarket_price(bean.getMarket_price()==null?0:bean.getMarket_price());
			p.setShop_price(bean.getShop_price()==null?0:bean.getShop_price());
			p.setPname(bean.getPname()==null?"":bean.getPname());
			p.setPdate(new Date());
			p.setStatus(bean.getStatus()==null?0:bean.getStatus());
			if(csid!=null&&!"".equals(csid)){
				CategorySecond cs = categorySecondService.find(csid);
				p.setCategorySecond(cs);
			}else{
				p.setCategorySecond(null);
			}
			p.setPdesc(bean.getPdesc()==null?"":bean.getPdesc());
			productService.save(p);
		}else{
			Product p = productService.findByPid(bean.getId());
			p.setAddress(bean.getAddress()==null?"":bean.getAddress());
			if(file != null && !"".equals(file)){
				String path = "E:\\project\\temp\\";
				String originalFilename = file.getOriginalFilename();
				if(originalFilename!=null&& !"".equals(originalFilename)){
					String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
					File file1 = new File(path+newFileName);
					
					file.transferTo(file1);
					//图片上传成功，设置user属性，写入数据库
					p.setImage(newFileName);
				}
				
			}
			p.setStatus(bean.getStatus()==null?0:bean.getStatus());
			p.setIs_hot(bean.getIs_hot()==null?"":bean.getIs_hot());
			p.setMarket_price(bean.getMarket_price()==null?0:bean.getMarket_price());
			p.setShop_price(bean.getShop_price()==null?0:bean.getShop_price());
			p.setPname(bean.getPname()==null?"":bean.getPname());
			p.setPdate(new Date());
			if(csid!=null&&!"".equals(csid)){
				CategorySecond cs = categorySecondService.find(csid);
				p.setCategorySecond(cs);
			}else{
				p.setCategorySecond(null);
			}
			p.setPdesc(bean.getPdesc()==null?"":bean.getPdesc());
			productService.update(p);
		}
		return "redirect:/product/adminProductList.action?page=1";
	}
	
	@RequestMapping("queryProduct.action")
	public String queryProduct(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String pname1 = request.getParameter("pname");
		String pname = null;
		if(pname1!=null&&!"".equals(pname1)){
			pname = new String(pname1.getBytes("iso-8859-1"),"UTF-8");
		}
		String shop_price_from = request.getParameter("shop_price_from");
		String shop_price_to = request.getParameter("shop_price_to");
		String pdateStart = request.getParameter("pdateStart");
		String pdateEnd = request.getParameter("pdateEnd");
		
		String address1 = request.getParameter("address");
		String address = null;
		if(address1!=null&&!"".equals(address1)){
			address = new String(address1.getBytes("iso-8859-1"),"UTF-8");
		}
		List<Product> productList = productService.findByInput(pname,shop_price_from,shop_price_to,pdateStart,pdateEnd,address);
		//System.out.println("pageBean-------------"+pageBean);
		//List<Product> productList = productService.findAll();
		request.setAttribute("productList", productList);
		
		return "/admin/product/queryProductList";
	}
	
}
