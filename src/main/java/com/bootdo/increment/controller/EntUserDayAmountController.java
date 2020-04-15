package com.bootdo.increment.controller;

import com.bootdo.Utils.ExcelUtilX;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbEntUserDayAmount;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.EntUserDayAmountService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 12:05
 **/
@Controller
@RequestMapping("/entUserDayAmountController")
public class EntUserDayAmountController extends BaseController {
    @Autowired
    private EntUserDayAmountService entUserDayAmountService;

//    入口
    @RequestMapping("/entUserDayAmount")
    public String entUserDayAmount(){
        return "increment/entUserDayAmount";
    }

    @PostMapping("/list")
    @ResponseBody
    public PageUtils selectEntUserDayAmountList(TbEntUserDayAmount entUserDayAmount) {
        PageUtils pageUtils = new PageUtils(
                entUserDayAmountService.selectEntUserDayAmountList(entUserDayAmount),
                entUserDayAmountService.selectEntUserDayAmountCount(entUserDayAmount)
        );
        return pageUtils;
    };




    @GetMapping("/excel")
    @ResponseBody
    public void excelEntUserDayAmountList(TbEntUserDayAmount tbEntUserDayAmount, HttpServletResponse response) {
        try {
            List<TbEntUserDayAmount> entUserDayAmounts = entUserDayAmountService.selectEntUserDayAmountList(tbEntUserDayAmount);
            String[] title = {
                    "统计日期", "渠道企业ID", "企业名称", "渠道", "员工增值总额（元）", "获取数据时间"
            };
            String excelUserValueAddFileName = "企业用户增值日报表.xlsx";
            Workbook wb = ExcelUtilX.Derived(title, entUserDayAmountService.excelAwardingOrderList(entUserDayAmounts));
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
