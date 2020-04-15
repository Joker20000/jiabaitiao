package com.bootdo.BackerUser.Controller;


import com.bootdo.BackerUser.Service.IEnterPriseService;
import com.bootdo.BackerUser.domain.Enterprise;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bku/bc")
public class IEnterPriseController extends BaseController {
    @Autowired
    private IEnterPriseService priseService;

    @GetMapping("/bc")
    String bc() {
        return "BUser/IEnterPirse";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        if(!"".equals(params.get("ename")) && params.get("ename") != null){
            params.put("ename",StringUtil.formatLike(String.valueOf(params.get("ename"))));
        }

        // 查询列表数据
        Query query = new Query(params);
        List<Enterprise> sysUserList = priseService.list(query);
        int total = priseService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        System.out.println("*****************************************"+pageUtil);
        return pageUtil;
    }
}
