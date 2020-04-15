package com.bootdo.increment.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.increment.dao.UserControlDao;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 23:12
 **/
@Service
public class UserControlServiceImpl implements UserControlService {
    @Autowired
    private UserControlDao userControlDao;

    @Override
    public List<TbIncrementUser> selectUserControlList(UserDTO userDTO) {
        return userControlDao.selectUserControlList(userDTO);
    }

    @Override
    public int selectUserControlCount(UserDTO userDTO) {
        return userControlDao.selectUserControlCount(userDTO);
    }

    @Override
    public List<List<String>> excelUserControlList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
            userList.add(userNew.getUserId().toString());
            userList.add(userNew.getUserName());
            userList.add(userNew.getUserMobile());
            userList.add(userNew.getUserEmail());
//            将数据库参数与实际显示文字做对应
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
            userList.add(userNew.getEntId());
            userList.add(userNew.getEntName());
            if (userNew.getCreateDate() == null || userNew.getCreateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getCreateDate()));
            }
            userLists.add(userList);
        }
        return userLists;
    }
}
