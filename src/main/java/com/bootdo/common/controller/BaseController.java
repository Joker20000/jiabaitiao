package com.bootdo.common.controller;

import com.bootdo.system.domain.UserToken;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class BaseController {
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    protected void export(Workbook wb, String fileName, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + (new String(fileName.getBytes("gb2312"), "ISO-8859-1")));
        response.flushBuffer();
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }


}