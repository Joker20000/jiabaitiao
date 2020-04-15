package com.bootdo.increment.controller;

import com.bootdo.Utils.ExcelUtilX;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserDayAmountService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 11:26
 **/
@Controller
@RequestMapping("/userDayAmountController")
public class UserDayAmountController extends BaseController {
    @Autowired
    private UserDayAmountService userDayAmountService;

    @RequestMapping("/userDayAmount")
    public String userDayAmount(){
        return "increment/userDayAmount";
    }

    /**
     * 用户增值日报表查询
     *
     * @param userDTO userName
     *                userMobile
     *                userEmail
     *                entName
     *                统计日期 前一天的时间
     *                statisticsDateStart
     *                statisticsDateEnd
     *                获取数据日期
     *                createDateStart
     *                createDateEnd
     *                分页
     *                offset
     *                limit
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageUtils selectUserDayAmountList(UserDTO userDTO) {
        PageUtils pageUtils = new PageUtils(
                userDayAmountService.selectUserDayAmountList(userDTO),
                userDayAmountService.selectUserDayAmountCount(userDTO)
        );
        return pageUtils;
    }

    /**
     * 用户增值日报表查询Excel导出
     *
     * @param userDTO  userName
     *                 userMobile
     *                 userEmail
     *                 entName
     *                 统计日期 前一天的时间
     *                 statisticsDateStart
     *                 statisticsDateEnd
     *                 获取数据日期
     *                 createDateStart
     *                 createDateEnd
     * @param response
     */
    @GetMapping("/excel")
    @ResponseBody
    public void excelUserDayAmountList(UserDTO userDTO, HttpServletResponse response) {
        try {
            List<TbIncrementUser> tbIncrementUserList = userDayAmountService.selectUserDayAmountList(userDTO);
            String[] title = {
                    "统计日期", "姓名", "手机号", "邮箱", "持有金额（元）", "渠道", "嘉薪企业ID", "企业名称",
                    "用户提现权益（元）", "获取数据时间"
            };
            String excelUserValueAddFileName = "用户增值日报表.xlsx";
            Workbook wb = ExcelUtilX.Derived(title, userDayAmountService.excelUserDayAmountList(tbIncrementUserList));
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
