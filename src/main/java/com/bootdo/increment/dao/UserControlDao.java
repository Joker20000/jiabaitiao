package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * 用户管理
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 22:53
 **/
public interface UserControlDao {
    /**
     * 用户信息查询
     *
     * @param userDTO
     * @return
     */
    List<TbIncrementUser> selectUserControlList(UserDTO userDTO);

    /**
     *
     * @param userDTO
     * @return
     */
    int selectUserControlCount(UserDTO userDTO);
}
