package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.FinanceCountDO;
import com.bootdo.system.service.FinanceCountService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 财务统计表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-18 16:22:07
 */
 
@Controller
@RequestMapping("/system/financeCount")
public class FinanceCountController {
	@Autowired
	private FinanceCountService financeCountService;
	
	@GetMapping()
	//@RequiresPermissions("system:financeCount:financeCount")
	String FinanceCount(){
	    return "system/financeCount/financeCount";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("system:financeCount:financeCount")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FinanceCountDO> financeCountList = financeCountService.list(query);
		int total = financeCountService.count(query);
		PageUtils pageUtils = new PageUtils(financeCountList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	//@RequiresPermissions("system:financeCount:add")
	String add(){
	    return "system/financeCount/add";
	}

	@GetMapping("/edit/{countId}")
	//@RequiresPermissions("system:financeCount:edit")
	String edit(@PathVariable("countId") Integer countId,Model model){
		FinanceCountDO financeCount = financeCountService.get(countId);
		model.addAttribute("financeCount", financeCount);
	    return "system/financeCount/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("system:financeCount:add")
	public R save( FinanceCountDO financeCount){
		if(financeCountService.save(financeCount)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("system:financeCount:edit")
	public R update( FinanceCountDO financeCount){
		financeCountService.update(financeCount);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	//@RequiresPermissions("system:financeCount:remove")
	public R remove( Integer countId){
		if(financeCountService.remove(countId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	//@RequiresPermissions("system:financeCount:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] countIds){
		financeCountService.batchRemove(countIds);
		return R.ok();
	}
	
}
