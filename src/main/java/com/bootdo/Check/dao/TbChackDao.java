package com.bootdo.Check.dao;
import com.bootdo.Check.domain.*;
import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TbChackDao {

    String findTbChack(@Param("chackno") String chackno);
    // 分页查询
    List<TbChack> list(Map<String, Object> map);
    // 查询总数
    int count(Map<String, Object> map);
    // 查询用户来源和身份信息 user
    List<TbUser> get(String chackno);
    // 查询同盾贷前结果 use
    TbTongdunResult getResult( String chackNo);
    // 审核总览Excel下载
    List<HashMap<String,Object>> CheExcelOnes(Map<String,Object> map);
    // 审核类型('01'.初审,'02'.复审)
    TbChackRecord selFSF(Map map);
    // 查询审核有效和授信额度
    TbChackType selParam();
    // 查询当前用户
    UserDO getUser(Long userId);
    // 添加信审记录
    int insert(TbUser student);
    // 修改联系地址
    int updateLice(TbAccountInfo tbAccountInfo);
    //修改信审状态
    int updateState(Map<String,Object> map);
    // 添加到历史表
    int insertHistory(TbChack history);
    // 手机运营商报告明细
    /* List<TbUserMobile> userMobiles(@Param("chackno") String chackno);*/

    /*  @Select("SELECT m.FILE_NAME as fileName,m.FILE_PATH as filePath FROM TB_CHACK c,TB_USER_MOBILE m WHERE c.CHACK_NO = m.CHACK_NO AND m.RESULT_STATE = 1 AND m.DATA_STATE = 1 AND m.CHACK_NO = #{chackno}")
     */ List<TbUserMobile> queryMobileList(@Param("accountNo") String accountNo);

    TbAccountInfo getCardid(@Param("chackNo") String chackNo);

    TbAccountInfo getlive(@Param("accountNo") String accountNo);

    List<TbUserLinkmanInfo> getUserLink(@Param("accountNo") String accountNo);

    @Select("select  CHACK_NO as chackNo , CHACK_TYPE as chackType, " +
            "ACCOUNT_NO as accountNo , USER_ID as userId from TB_CHACK where CHACK_NO = #{chackNo} and VALID = '1'")
    Map<String,String> queryChackMap(@Param("chackNo") String chackNo);

    @Select("select ACCOUNT_NO as accountNo , CREDIT_LIMIT as creditLimit, USABLE_LIMIT as usableLimit " +
            "from TB_ACT_ACCOUNT where ACCOUNT_NO = #{accountNo}")
    Map<String,String> queryAccountMap(@Param("accountNo") String accountNo);

    @Select("select CREDIT_LINE as creditLine from TB_CHACK_TYPE where CHACK_TYPE = #{chackType}")
    Map<String,String> queryChackTypeMap(@Param("chackType") String chackType);

    TbChackRecord selGroup(@Param("chackno") String chackno, @Param("type") String type);

    @Update("update TB_ACCOUNT_INFO set PROVE_STATE = #{states},LAST_TIME = now() WHERE ACCOUNT_NO=#{accountNo}")
    void updateAccountNo(@Param("accountNo") String accountNo,@Param("states") String states);
    @Select("SELECT u.USER_ID as userId, u.MOBILE as mobile, u.USER_CHANNEL_ID as userChannelId, c.CHACK_NO as chackNo FROM TB_USER u,TB_CHACK c WHERE u.USER_ID = c.USER_ID and c.CHACK_NO = #{chackNo} and c.VALID = '1'")
    Map<String,String> getMobile(@Param("chackNo") String chackNo);

    @Update("update TB_ACCOUNT_INFO set ADDRESS_SITUATION = #{addressSituation},LIVE_ADDRESS = #{liveAddress}  WHERE ACCOUNT_NO=#{accountNo}")
    void updateUserAddress(TbAccountInfo tbAccountInfo);
}
