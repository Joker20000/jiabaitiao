package com.bootdo.Check.dao;

import com.bootdo.Check.domain.CheExcel;
import com.bootdo.Check.domain.Checker;
import com.bootdo.Check.domain.Enter;
import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ICheckDao {
    List<Checker> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    Enter get(@Param("chackno") String chackno);

    int insert(Enter student);

    UserDO getUser(Long userId);

    int update(Enter ente);

    int insertHistory(Enter history);

    List<Enter> pictures(String chackno);

    String selAct(int eid);

    @Update("UPDATE t_enterprise SET state = #{state} WHERE e_id = #{eid}")
    void updateUserState(@Param("eid") int eid, @Param("state") String state);

    Enter selFSF(@Param("chackno") String chackno, @Param("type") String type);

    List<HashMap<String, Object>> CheExcelOne(Map<String, Object> map);

    List<CheExcel> CheExcelTwo(Map<String, Object> map);

    int addTime(@Param("eid") int eid, @Param("losedate") String losedate);

    Enter selParam();

    Enter selGroup(@Param("chackno") String chackno, @Param("type") String type);

    int updateState(@Param("chackno") String chackno, @Param("state") String state);

    Map<String, Object> searchUserJFidByUserId(@Param("userid")String userid);
}
