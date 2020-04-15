package com.bootdo.User.Controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bootdo.User.Service.UserNService;
import com.bootdo.User.domain.UserN;
import com.bootdo.Utils.PageBean;
import com.bootdo.Utils.ResponseUtil;
import com.bootdo.Utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * 用户Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserNController {

	@Resource
	private UserNService userNService;

	/**
	 * 用户登录
	 * @param userN
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(UserN userN, HttpServletRequest request){
		String password = userN.getPassword();
		//将密码进行MD5加密处理
		String passwordMD5 = md5Password(password);
		userN.setPassword(passwordMD5);
		UserN resultUserN = userNService.login(userN);
		if(resultUserN ==null){
			userN.setPassword(password);
			request.setAttribute("userN", userN);
			request.setAttribute("errorMsg", "用户名或密码错误！");

			return "login";
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", resultUserN);
			return "redirect:/main.jsp";
		}
	}

	/**
	 * 获取客户经理信息 下拉框数据用到
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/customerManagerComboList")
	public String customerManagerComboList(HttpServletResponse response)throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("trueName", "");
		jsonObject.put("trueName", "请选择...");
		jsonArray.add(jsonObject);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roleName", "客户经理");
		List<UserN> userNList = userNService.findUser(map);
		JSONArray rows=JSONArray.fromObject(userNList);
		jsonArray.addAll(rows);
		ResponseUtil.write(response, jsonArray);
		return null;
	}

	/**
	 * 修改用户密码
	 * @param userN
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(UserN userN, HttpServletResponse response)throws Exception{
		//将密码进行MD5加密处理
		String password = md5Password(userN.getPassword());
		userN.setPassword(password);
		int resultTotal= userNService.updateUser(userN);
		JSONObject result=new JSONObject();
		if(resultTotal>0){ // 执行成功
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 用户注销
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/login.jsp";
	}

	/**
	 * 查询用户集合
	 * @param page
	 * @param rows
	 * @param s_userN
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, UserN s_userN, HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userName", StringUtil.formatLike(s_userN.getUserName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<UserN> userNList = userNService.findUser(map);
		Long total= userNService.getTotalUser(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(userNList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 添加用户
	 * @param customer
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(UserN userN, HttpServletResponse response)throws Exception{
		//将密码进行MD5加密处理
		String password = md5Password(userN.getPassword());
		userN.setPassword(password);
		int resultTotal=0; // 操作的记录条数
		if(userN.getId()==null){
			resultTotal= userNService.addUser(userN);
		}else{
			resultTotal= userNService.updateUser(userN);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){ // 执行成功
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 删除用户
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			userNService.deleteUser(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 生成32位md5码
	 * @param password
	 * @return
	 */
	public static String md5Password(String password) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}

	}
}

