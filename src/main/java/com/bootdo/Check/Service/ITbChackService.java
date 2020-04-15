package com.bootdo.Check.Service;
import com.bootdo.Check.domain.*;
import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ITbChackService {

    String findTbChack(@Param("chackno") String chackno);
    //分页查询
    List<TbChack> list(Map<String, Object> map);
    //查询总数
    int count(Map<String, Object> map);
    // 查询用户来源
    List<TbUser> get(String chackno);
    // 查询同盾贷前结果
    TbTongdunResult getResult(String chackNo);
    // 审核总览Excel下载
    List<HashMap<String,Object>> CheExcelOnes(Map<String,Object> map);
    // 审核类型('01'.初审,'02'.复审)
    TbChackRecord selFSF(String chackNo, String type);
    // 查询审核有效和授信额度
    TbChackType selParam();
    // 查询当前用户
    UserDO getUser(Long userId);
    // 添加信审记录
    int insert(TbUser student);
    // 修改联系地址
    int updateLice(TbAccountInfo tbAccountInfo);
    // 修改信审状态
    int updateState(Map<String,Object> map);
    // 添加到历史表
    int insertHistory(TbChack history);
    // 手机运营商报告明细
    List<TbUserMobile> queryMobileList(String accountNo);
    // 身份证
    TbAccountInfo getCardid(String chackNo);
    // 查询地址信息
    TbAccountInfo getlive(String accountNo);

    List<TbUserLinkmanInfo> getUserLink(String accountNo);

    Map<String,String> queryChackMap(String chackNo);

    Map<String,String> queryAccountMap(String accountNo);

    Map<String,String> queryChackTypeMap(String chackType);

    TbChackRecord selGroup(String chackno,String type);

    void updateAccountNo(String accountNo,String states);

    Map<String,String> getMobile(String chackNo);

    void updateUserAddress(TbAccountInfo tbAccountInfo);
}
