package com.bootdo.Repayment.Controller;
import com.bootdo.Repayment.Service.IRepayService;
import com.bootdo.Repayment.domain.Repay;
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
@RequestMapping("/rt/re")
public class IRepayController extends BaseController {
    @Autowired
    private IRepayService priseService;

    @GetMapping("/re")
    String bc() {
        return "Rep/Repay";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        if(!"".equals(params.get("cflow")) && params.get("cflow") != null){
            params.put("cflow",StringUtil.formatLike(String.valueOf(params.get("cflow"))));
        }
        if(!"".equals(params.get("repaynum")) && params.get("repaynum") != null){
            params.put("repaynum",StringUtil.formatLike(String.valueOf(params.get("repaynum"))));
        }
        if(!"".equals(params.get("loannum")) && params.get("loannum") != null){
            params.put("loannum",StringUtil.formatLike(String.valueOf(params.get("loannum"))));
        }


        // 查询列表数据
        Query query = new Query(params);
        List<Repay> sysUserList = priseService.list(query);
        int total = priseService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        System.out.println("*****************************************"+pageUtil);
        return pageUtil;
    }
}
