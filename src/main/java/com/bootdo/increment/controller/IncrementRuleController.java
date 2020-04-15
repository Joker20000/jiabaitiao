package com.bootdo.increment.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbIncrementParameter;
import com.bootdo.increment.domain.TbIncrementRule;
import com.bootdo.increment.service.IncrementRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 增值产品规则
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-14 16:03
 **/
@RequestMapping("/incrementRuleController")
@Controller
public class IncrementRuleController {
    @Autowired
    private IncrementRuleService incrementRuleService;

    @RequestMapping("/incrementRule")
    public String incrementRule(){
        return "increment/incrementRule";
    }

    /**
     * 增值产品规则查询
     *
     * @param incrementRule
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageUtils selectIncrementRuleList(TbIncrementRule incrementRule) {
        PageUtils pageUtils = new PageUtils(

                incrementRuleService.selectIncrementRuleList(incrementRule),
                incrementRuleService.selectIncrementRuleCount(incrementRule)
        );
        return pageUtils;
    }

    /**
     * 个人增值持有限额和增值产品总限额查询
     *
     * @return
     */
    @GetMapping("/selectIncrementParameter")
    @ResponseBody
    public TbIncrementParameter selectIncrementParameter() {
        return incrementRuleService.selectIncrementParameter();
    }

}
