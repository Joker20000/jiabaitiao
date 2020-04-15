package com.bootdo.increment.controller;

import com.bootdo.Utils.ExcelUtilX;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserTransactionService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户交易记录查询，转入转出 controller
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-13 13:16
 **/
@Controller
@RequestMapping("/userTransactionController")
public class UserTransactionController extends BaseController {
    @Autowired
    private UserTransactionService userTransactionService;

    @RequestMapping("/userTransaction")
    public String userTransaction() {
        return "increment/userTransaction";
    }

    /**
     * 用户交易记录查询
     *
     * @param userDTO userName 用户姓名
     *                userMobile 手机号
     *                userEmail 邮箱
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                transferType 交易类型 1-转入（冻结）、2-转出（解冻）
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                transferState 交易状态 1-成功 2-处理中 3-失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                完成时间
     *                complateDateStart
     *                complateDateEnd
     *                userPageon 当前页
     *                userLimit 分页开关
     * @return List<TbIncrementUser>
     */
    @GetMapping("/list")
    @ResponseBody
    public PageUtils selectUserTransactionList(UserDTO userDTO) {
        PageUtils pageUtils = new PageUtils(
                userTransactionService.selectUserTransactionList(userDTO),
                userTransactionService.selectUserTransactionCount(userDTO));
        return pageUtils;
    }

    /**
     * 交易记录导出excel
     *
     * @param userDTO userName 用户姓名
     *                userMobile 手机号
     *                userEmail 邮箱
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                transferType 交易类型 1-转入（冻结）、2-转出（解冻）
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                transferState 交易状态 1-成功 2-处理中 3-失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                完成时间
     *                complateDateStart
     *                complateDateEnd
     *                userLimit 分页开关
     * @return
     */
    @GetMapping("/excel")
    @ResponseBody
    public void excelUserTransactionList(UserDTO userDTO, HttpServletResponse response) {
        try {
            List<TbIncrementUser> tbIncrementUserList = userTransactionService.selectUserTransactionList(userDTO);
            String[] title = {
                    "交易流水号", "姓名", "手机号", "邮箱", "用户渠道", "企业名称", "金额（元）",
                    "交易类型", "账户类型", "交易状态", "交易创建时间", "交易完成时间"
            };
            String excelUserValueAddFileName = "转入转出交易记录.xlsx";
            Workbook wb = ExcelUtilX.Derived(title, userTransactionService.excelUserTransactionList(tbIncrementUserList));
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
