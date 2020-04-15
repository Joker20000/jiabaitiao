package com.bootdo.increment.controller;

import com.bootdo.Utils.ExcelUtilX;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserValueAddedService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 员工增值持有数据业务
 * selectUserValueAdded
 * excelUserValueAddList
 * 增值奖励数据业务
 * selectValueAddRewardList
 * excelValueAddRewardList
 * 奖励发放订单业务
 * selectAwardingOrderList
 * excelAwardingOrderList
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 21:06
 **/
@Controller
@RequestMapping("/userValueController")
public class UserValueAddedController extends BaseController {
    @Autowired
    private UserValueAddedService userValueAddedService;

    @RequestMapping("/userValue")
    public String userValue() {
        return "increment/userValue";
    }

    @RequestMapping("/awardingOrder")
    public String awardingOrder() {
        return "increment/awardingOrder";
    }

    /**
     * 员工增值持有数据查询+分页
     *
     * @param userDTO 查询条件
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName   企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                更新时间
     *                updateDateStart
     *                updateDateEnd
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                userPageon当前页
     * @return PageUtils：数据条数+查询信息集合
     */
    @GetMapping("/selectUserValueAdded")
    @ResponseBody
    public PageUtils selectUserValueAdded(UserDTO userDTO) {
        List<TbIncrementUser> tbIncrementUserList = userValueAddedService.selectUserValueAddList(userDTO);
        PageUtils pageUtils = new PageUtils(
                tbIncrementUserList,
                userValueAddedService.selectUserValueAddCount(userDTO)
        );
        return pageUtils;
    }

    /**
     * 员工增值持有数据Excel导出
     *
     * @param userDTO  查询条件
     *                 userName
     *                 userMobile
     *                 userEmail
     *                 userChannelId 用户渠道 01嘉福 02嘉薪
     *                 entName   企业名称
     *                 accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                 更新时间
     *                 updateDateStart
     *                 updateDateEnd
     *                 创建时间
     *                 createDateStart
     *                 createDateEnd
     * @param response
     */
    @GetMapping("/excelUserValueAddList")
    @ResponseBody
    public void excelUserValueAddList(UserDTO userDTO, HttpServletResponse response) {
        try {
            List<TbIncrementUser> tbIncrementUserList = userValueAddedService.selectUserValueAddList(userDTO);
            String[] title = {"姓名", "手机号", "邮箱", "用户渠道", "企业名称", "持有金额(元)", "增值账户类型", "更新时间", "创建时间"};
            String excelUserValueAddFileName = "员工增值持有表.xlsx";
            List<List<String>> excelUserList = userValueAddedService.excelUserValueAddList(tbIncrementUserList);
            Workbook wb = ExcelUtilX.Derived(title, excelUserList);
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 增值奖励查询
     *
     * @param userDTO 要查询的用户信息
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                orderId 奖励发放订单号
     *                orderState 发放状态  0-待发放 1-发放成功 2-发放中 3-发放失败
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     *                userPageon当前页
     * @return
     */
    @PostMapping("/selectValueAddRewardList")
    public PageUtils selectValueAddRewardList(@RequestBody UserDTO userDTO) {
        PageUtils pageUtils = new PageUtils(
                userValueAddedService.selectValueAddRewardList(userDTO),
                userValueAddedService.selectValueAddRewardCount(userDTO));
        return pageUtils;
    }

    /**
     * 增值奖励查询excel下载
     *
     * @param userDTO  要查询的用户信息
     *                 userName
     *                 userMobile
     *                 userEmail
     *                 userChannelId 渠道 01嘉福 02嘉薪
     *                 entName 企业名称
     *                 accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                 orderId 奖励发放订单号
     *                 orderState 发放状态  0-待发放 1-发放成功 2-发放中 3-发放失败
     *                 统计日期
     *                 statisticsDateStart
     *                 statisticsDateEnd
     * @param response
     */
    @GetMapping("/excelValueAddRewardList")
    public void excelValueAddRewardList(UserDTO userDTO, HttpServletResponse response) {
        try {
            List<TbIncrementUser> tbIncrementUserList = userValueAddedService.selectValueAddRewardList(userDTO);
            String[] title = {"统计日期", "姓名", "手机号", "邮箱", "用户渠道", "企业名称",
                    "账户类型", "当日转入金额（元）", "持有金额（元）", "年化率", "当日奖励金额（元）", "累计奖励金额（元）",
                    "关联发放订单号", "发放状态", "统计时间"};
            String excelUserValueAddFileName = "增值奖励表.xlsx";
            List<List<String>> excelUserList = userValueAddedService.excelValueAddRewardList(tbIncrementUserList);
            Workbook wb = ExcelUtilX.Derived(title, excelUserList);
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 奖励发放订单查询
     *
     * @param userDTO 查询条件
     *                orderId奖励发放订单号
     *                extOrderId嘉福流水号
     *                userName
     *                userMobile
     *                userEmail
     *                entName 企业名称
     *                userChannelId用户渠道： 01嘉福 02嘉薪
     *                orderState发放状态：0-待发放 1-发放成功 2-发放中 3-发放失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                交易完成时间
     *                complateDateStart
     *                complateDateEnd
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     *                userPageon 第几条开始查
     * @return
     */
    @PostMapping("/selectAwardingOrderList")
    public PageUtils selectAwardingOrderList(@RequestBody UserDTO userDTO) {
        PageUtils pageUtils = new PageUtils(
                userValueAddedService.selectAwardingOrderList(userDTO),
                userValueAddedService.selectAwardingOrderCount(userDTO)
        );
        return pageUtils;
    }

    /**
     * 及奖励发放订单查询数据Excel导出
     *
     * @param userDTO
     * @param response
     */
    @GetMapping("/excelAwardingOrderList")
    public void excelAwardingOrderList(UserDTO userDTO, HttpServletResponse response) {
        try {
            List<TbIncrementUser> tbIncrementUserList = userValueAddedService.selectAwardingOrderList(userDTO);
            String[] title = {"发放订单号", "嘉福流水号", "姓名", "手机号", "邮箱", "用户渠道", "企业名称",
                    "工资增值奖励（元）", "福利增值奖励（元）", "现金增值奖励（元）", "总奖励", "发放福豆（颗）", "福豆发放状态",
                    "统计日期", "订单创建时间", "订单发放时间", "订单完成时间"};
            String excelUserValueAddFileName = "奖励发放订单.xlsx";
            List<List<String>> excelUserList = userValueAddedService.excelAwardingOrderList(tbIncrementUserList);
            Workbook wb = ExcelUtilX.Derived(title, excelUserList);
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
