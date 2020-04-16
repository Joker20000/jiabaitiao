package com.bootdo.increment.controller;

import com.bootdo.Utils.ExcelUtilX;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserControlService;
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
 * @Date : 2020-04-12 23:13
 **/
@Controller
@RequestMapping("/userControlController")
public class UserControlController extends BaseController {
    @Autowired
    private UserControlService userControlService;

    @RequestMapping("/userControl")
    public String userControl(){
        return "increment/userControl";
    }


    @GetMapping("/list")
    @Log("用户信息查询")
    @ResponseBody
    public PageUtils selectUserControlList(UserDTO userDTO) {
        PageUtils pageUtils = new PageUtils(
                userControlService.selectUserControlList(userDTO),
                userControlService.selectUserControlCount(userDTO));
        return pageUtils;
    }

    @GetMapping("/excel")
    @Log("用户信息查询Excel导出")
    @ResponseBody
    public void excelUserControlList(UserDTO userDTO, HttpServletResponse response) {
        try {
            userDTO.setLimit(null);
            userDTO.setOffset(null);
            List<TbIncrementUser> tbIncrementUserList = userControlService.selectUserControlList(userDTO);
            String[] title = {
                    "用户ID", "姓名", "手机号", "邮箱", "用户渠道", "企业ID", "企业名称", "开通时间"
            };
            String excelUserValueAddFileName = "用户管理.xlsx";
            Workbook wb = ExcelUtilX.Derived(title, userControlService.excelUserControlList(tbIncrementUserList));
            export(wb, excelUserValueAddFileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
