package com.bootdo.Account.Controller;

import com.bootdo.Account.Service.FlowService;
import com.bootdo.Account.domain.Accounter;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ac/fl")
public class FlowController extends BaseController {
    @Autowired
    FlowService flowService;

    @GetMapping("/fl")
    String bc() {
        return "Acc/Flow";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        if(!"".equals(params.get("ceid")) && params.get("ceid") != null){
            params.put("ceid",StringUtil.formatLike(String.valueOf(params.get("ceid"))));
        }
        if(!"".equals(params.get("ename")) && params.get("ename") != null){
            params.put("ename",StringUtil.formatLike(String.valueOf(params.get("ename"))));
        }
        if(!"".equals(params.get("relevantorder")) && params.get("relevantorder") != null){
            params.put("relevantorder",StringUtil.formatLike(String.valueOf(params.get("relevantorder"))));
        }

        // 查询列表数据
        Query query = new Query(params);
        List<Accounter> sysUserList = flowService.list(query);
        System.out.println("*****************************************"+sysUserList);
        int total = flowService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        System.out.println("*****************************************"+pageUtil);
        return pageUtil;
    }
}
