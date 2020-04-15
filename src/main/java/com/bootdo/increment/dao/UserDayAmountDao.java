package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**用户增值日报表Dao
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-14 18:00
 **/
public interface UserDayAmountDao {
    /**
     * 用户增值日报表查询
     * @param userDTO
     *          userName
     *          userMobile
     *          userEmail
     *          entName
     *          统计日期
     *          statisticsDateStart
     *          statisticsDateEnd
     * @return
     */
    List<TbIncrementUser> selectUserDayAmountList(UserDTO userDTO);

    /**
     * 用户增值日报表查询count
     * @param userDTO
     * @return
     */
    int selectUserDayAmountCount(UserDTO userDTO);
}
