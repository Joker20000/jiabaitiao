package com.bootdo.BlackList.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.BlackList.Service.BlackListService;
import com.bootdo.BlackList.domain.AddBlackList;
import com.bootdo.BlackList.domain.Blacklist;
import com.bootdo.User.Service.UserNService;
import com.bootdo.UserManage.Controller.DateJsonValueProcessor;
import com.bootdo.UserManage.domain.UserJBT;
import com.bootdo.Utils.PageBean;
import com.bootdo.Utils.ResponseUtil;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/*
 *  黑名单
 * */
@Controller
@RequestMapping("/blackListController")
public class BlackListController extends BaseController {

	@Autowired
	private BlackListService blacklistService;

	@Autowired
	private UserNService userNService;
	@GetMapping("/bl")
	String bl() {
		return "BlackList/BlackList";
	}

	@GetMapping("/blacklistAdd")
	String blacklistAdd() {
		return "BlackList/BlacklistAdd";
	}
	//查询
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		if(!"".equals(params.get("realname")) && params.get("realname") != null){
			params.put("realname",StringUtil.formatLike(String.valueOf(params.get("realname"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("companyName")) && params.get("companyName") != null){
			params.put("companyName",StringUtil.formatLike(String.valueOf(params.get("companyName"))));
		}
		Query query = new Query(params);
		List<Blacklist>  blackListList=blacklistService.findBlackListByCondition(query);
		Long total1=blacklistService.findBlackListCountByCondition(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(blackListList, total);
		return pageUtils;
	}


	//修改批量有效
	@RequestMapping("/updateBlackListState")
	@RequiresPermissions("BlackList:BlacklistHave")
	public String updateBlackListState(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{

		JSONObject result=new JSONObject();
		String [] idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			String state=blacklistService.findStateByuserId(Integer.parseInt(idsStr[i]));
			if(("0").equals(state)){  //0无效 1有效
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt=new Date();
				blacklistService.updateBlackListState(Integer.parseInt(idsStr[i]), "1",f.format(dt));
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}


	//修改批量无效
	@RequestMapping("/updateBlackListState1")
	@RequiresPermissions("BlackList:BlacklistNone")
	public String updateBlackListState1(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String [] idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			String state=blacklistService.findStateByuserId(Integer.parseInt(idsStr[i]));
			if(("1").equals(state)){ //0无效 1有效
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt=new Date();
				blacklistService.updateBlackListState(Integer.parseInt(idsStr[i]), "0",f.format(dt));
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}



	//增加黑名单
	@ResponseBody
	@GetMapping("/addBlackList")
	@RequiresPermissions("BlackList:BlacklistAdd")
	public PageUtils addBlackList(@RequestParam Map<String, Object> params) {
		if(!"".equals(params.get("realname")) && params.get("realname") != null){
			params.put("realname",StringUtil.formatLike(String.valueOf(params.get("realname"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("companyName")) && params.get("companyName") != null){
			params.put("companyName",StringUtil.formatLike(String.valueOf(params.get("companyName"))));
		}
		Query query = new Query(params);
		List<UserJBT> UserJBTList=  userNService.findUserByCondition(query);
		Long total1= userNService.findUserByConditionCount(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(UserJBTList, total);
		return pageUtils;
	}


	//根据用户id判断用户是否存在黑名单，以及黑名单的有效性验证
	@RequestMapping("/insertBlackListState")
	public  String  insertBlackListState(@RequestParam(value="ids")String ids,@RequestParam(value="Remarks",required=false)String Remarks,HttpServletRequest request ,HttpServletResponse response){
		String chackName = getUsername();
		System.out.println(chackName);
		JSONObject result=new JSONObject();
		String [] idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			String state=blacklistService.findStateByuserId(Integer.parseInt(idsStr[i]));
			System.out.print(state);
			if(("").equals(state)||state==null){
				//添加黑名单
				AddBlackList blackList=new AddBlackList();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt=new Date();

				blackList.setUserId(Integer.parseInt(idsStr[i]));
				blackList.setOperateMan(chackName);//取当前登录用户
				blackList.setCreateTime(f.format(dt));
				blackList.setOperateTime(f.format(dt));
				blackList.setLastTime(f.format(dt));
				blackList.setState("1");
				blackList.setReason(Remarks);
				blacklistService.addBlackListInfo(blackList);
			}

			if(("0").equals(state)){ //0无效 1有效

				AddBlackList blackList=new AddBlackList();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dt=new Date();

				blackList.setUserId(Integer.parseInt(idsStr[i]));
				blackList.setOperateMan(chackName);//取当前登录用户
				blackList.setCreateTime(f.format(dt));
				blackList.setOperateTime(f.format(dt));
				blackList.setLastTime(f.format(dt));
				blackList.setState("1");
				blackList.setReason(Remarks);
				blacklistService.updateBlackListState1(blackList);
			}

		}
		result.put("success", true);
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;


	}




}
