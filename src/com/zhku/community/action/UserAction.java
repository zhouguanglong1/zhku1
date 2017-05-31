package com.zhku.community.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




import com.zhku.base.action.BaseAct;
import com.zhku.community.bean.sys.User;
import com.zhku.community.service.UserService;


@Controller
@RequestMapping("/user")
public class UserAction extends BaseAct {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("testListUI.action")
	public String dataListUI(HttpServletRequest request){
		return "/user/testList";
	}
	
	@RequestMapping("userStatList.action")
	public String userStatList(HttpServletRequest request){
		List<Object[]> dataList = userService.statUser();
		List<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();
		for(int i = 0;i < dataList.size();i++){
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("role", dataList.get(i)[0]==null?"":dataList.get(i)[0]);
			map.put("count", dataList.get(i)[1]==null?"":dataList.get(i)[1]);
			maps.add(map);
		}
		request.setAttribute("maps", maps);
		
		return "/admin/user/statUserList";
	}
	
	
	
	@RequestMapping("userList.action")
	public String userList(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//String sql = "select * from sys_user";
		List<User> userList = userService.findAll();
		request.setAttribute("userList", userList);
		
		return "/admin/user/userList1";
	}
	
	@RequestMapping("CheckImg.action")
	public void CheckImg(HttpServletRequest request,HttpServletResponse response){
		int width = 120;
		int height = 30;

		// 步骤一 绘制一张内存中图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 步骤二 图片绘制背景颜色 ---通过绘图对象
		Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
		// 绘制任何图形之前 都必须指定一个颜色
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);

		// 步骤三 绘制边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 步骤四 四个随机数字
		Graphics2D graphics2d = (Graphics2D) graphics;
		// 设置输出字体
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));

		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		// String words =
		// "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";
		Random random = new Random();// 生成随机数
		// 定义StringBuffer
		StringBuffer sb = new StringBuffer();
		// 定义x坐标
		int x = 10;
		for (int i = 0; i < 4; i++) {
			// 随机颜色
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 旋转 -30 --- 30度
			int jiaodu = random.nextInt(60) - 30;
			// 换算弧度
			double theta = jiaodu * Math.PI / 180;

			// 生成一个随机数字
			int index = random.nextInt(words.length()); // 生成随机数 0 到 length - 1
			// 获得字母数字
			char c = words.charAt(index);
			sb.append(c);
			// 将c 输出到图片
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}

		// 将生成的字母存入到session中
		request.getSession()
				.setAttribute("checkcode", sb.toString());

		// 步骤五 绘制干扰线
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}

		// 将上面图片输出到浏览器 ImageIO
		graphics.dispose();// 释放资源
		try {
			ImageIO.write(bufferedImage, "jpg", response
					.getOutputStream());
			System.out.println(sb.toString());
			request.getSession().setAttribute("code", sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "";
	}
	/**
	 * 取其某一范围的color
	 * 
	 * @param fc
	 *            int 范围参数1
	 * @param bc
	 *            int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// 取其随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	@RequestMapping("regist.action")
	public String regist(HttpServletRequest request,HttpServletResponse response,User user,String checkcode,
			@RequestParam MultipartFile photo
			) throws Exception{
		// 从Session中获得存的验证码
		String checkcode1 = (String) request.getSession().getAttribute("code");
		if( checkcode == null || !checkcode.equalsIgnoreCase(checkcode1)){
			response.getWriter().print("<font color='red'>验证码为空或者不正确</font>");
			return "/register";
		}else{
			//进行图片上传
			if(photo != null && !"".equals(photo)){
				String path = "E:\\project\\temp\\";
				String originalFilename = photo.getOriginalFilename();
				String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
				File file = new File(path+newFileName);
				
				photo.transferTo(file);
				//图片上传成功，设置user属性，写入数据库
				user.setPhotoPath(newFileName);
			}else{
				user.setPhotoPath("");
			}
			user.setAddTime(new Date());
			user.setStatus("1");
			user.setRoleStatus("1");//表示普通用户
			userService.save1(user);
			request.getSession().setAttribute("user", user);
			
			return "/registerSuccess";
		}
		
	}
	
	
	@RequestMapping("checkUserName.action")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		User existUser = userService.findByUserName(username);
		response.setContentType("text/html;charset=UTF-8");
		if(existUser == null){
			// 用户名可以使用的
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else{
			// 用户名已经存在
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		}
	}
	
	@RequestMapping("login.action")
	public String login(HttpServletRequest request,HttpServletResponse response,
			String username,String password,String checkcode
			) throws IOException{
		// 从Session中获得存的验证码
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String checkcode1 = (String) request.getSession().getAttribute("code");
		if( checkcode == null || !checkcode.equalsIgnoreCase(checkcode1)){
			response.getWriter().print("<font color='red'>验证码为空或者不正确</font>");
			return "/login";
		}
		boolean isExist = userService.findByUserNameAndPassword(username,password);
		if(isExist){
			request.getSession().setAttribute("username", username);
			return "/loginSuccess";
		}else{
			//response.getWriter().print("<script language=\"javascript\">$('code1').html='<font color='red'>用户名或密码不存在</font>';</script>");
			return "/login";
		}
		
	}
	
	@RequestMapping("quit.action")
	public String quit(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
		return "/login";
	}
	
	@RequestMapping("quitAdmin.action")
	public String quitAdmin(HttpServletRequest request,HttpServletResponse response){
		request.getSession().invalidate();
		return "/admin/index";
	}
	
	
	@RequestMapping("unableUser.action")
	public String unableUser(Long uid,HttpServletRequest request,HttpServletResponse response){
		
		User user = userService.getUser(uid);
		//attr.addAttribute("user",u);
		user.setStatus("0");
		userService.update(user);
		//return "redirect:/user/updateUser.action";
		return "redirect:/user/userList.action";
	}
	
	@RequestMapping("enableUser.action")
	public String enableUser(Long uid,HttpServletRequest request,HttpServletResponse response){
		
		User u = userService.getUser(uid);
		u.setStatus("1");
		/*attr.addAttribute("user",u);
		attr.addAttribute("hehe","fdshjfkdsjf");*/
		//attr.addFlashAttribute("user", u);
		userService.update(u);
		return "redirect:/user/userList.action";
	}
	
	@RequestMapping("updateUser.action")
	public String updateUser(User user,HttpServletRequest request,HttpServletResponse response){
		//User user= request.getParameter("user");
		//String hehe = (String) request.getAttribute("hehe");
		//System.out.println(hehe);
		if(user!=null && !"".equals(user)){
			user.setStatus("0");
			userService.update(user);
		}
		return "redirect:/user/userList.action";
	}
	
	@RequestMapping("deleteUser.action")
	public String deleteUser(Long uid,HttpServletRequest request,HttpServletResponse response){
		//User u = userService.getUser(uid);
		if(uid!=null&&!"".equals(uid)){
			userService.delete(uid);
		}
		
		return "redirect:/user/userList.action";
	}
	
	@RequestMapping("ValidateAdmin.action")
	public String ValidateAdmin(HttpServletRequest request,HttpServletResponse response){
		String username1 = request.getParameter("username");
		String username = null;
		if(username1!=null && !"".equals(username1)){
			try {
				username = new String(username1.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String password = request.getParameter("password");
		boolean isExit = userService.validateAdmin(username,password);
		User user = userService.getUserByUserNameAndPassword(username,password);
		request.getSession().setAttribute("adminUser", user);
		if(isExit){
			return "/admin/boot";
		}else{
			//attr.addFlashAttribute("message", "用户名或密码错误！");
			request.setAttribute("message", "用户名或密码错误！");
			return "/admin/index";
		}
		//System.out.println("username:"+username+"------------------password:"+password );
		
	}
	
	
	
	@RequestMapping("queryUser.action")
	public String queryUser(HttpServletRequest request,HttpServletResponse response){
		String username1 = request.getParameter("username");
		String username = null;
		if(username1!=null && !"".equals(username1)){
			try {
				username = new String(username1.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String status = request.getParameter("status");
		String roleStatus = request.getParameter("roleStatus");
		List<User> userList = userService.findByQuery(username,status,roleStatus);
		request.setAttribute("userList", userList);
		return "/admin/user/userList1";
	}
	
}
