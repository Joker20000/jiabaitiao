package com.bootdo.Check.Service;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.Check.domain.CheExcel;
import com.bootdo.Check.domain.Checker;
import com.bootdo.Check.domain.Enter;
import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CheckService {
    List<Checker> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    Enter get(String chackno);

    int insert(Enter student);

    UserDO getUser(Long userId);

    int update(Enter ente);

    int insertHistory(Enter history);

    List<Enter> pictures(String chackno);

    String selAct(int eid);

    /**
     * 更新用户状态
     * @param eid
     * @param state
     */
    void updateUserState(int eid, String state);

    Enter selFSF(String chackno,String type);

    List<HashMap<String,Object>> CheExcelOne(Map<String,Object> map);

    List<CheExcel> CheExcelTwo(Map<String,Object> map);

    int addTime(int eid,String losedate);

    Enter selParam();

    Enter selGroup(@Param("chackno") String chackno, @Param("type") String type);


    int updateState(@Param("chackno")String chackno,@Param("state")String state);

    Map<String, Object> searchUserJFidByUserId(String accountNo);

    public Map<String, Object> searchJFUserDataByJFid(String jfid);
}
