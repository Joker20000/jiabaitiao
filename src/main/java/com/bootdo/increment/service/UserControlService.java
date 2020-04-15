package com.bootdo.increment.service;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 23:11
 **/
public interface UserControlService {
    /**
     * 用户信息查询
     * @param userDTO
     * @return
     */
    List<TbIncrementUser> selectUserControlList(UserDTO userDTO);

    int selectUserControlCount(UserDTO userDTO);

    List<List<String>> excelUserControlList(List<TbIncrementUser> users);
}
