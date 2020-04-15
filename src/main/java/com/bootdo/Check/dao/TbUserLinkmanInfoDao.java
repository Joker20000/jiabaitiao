package com.bootdo.Check.dao;

import com.bootdo.Check.domain.TbUserLinkmanInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbUserLinkmanInfoDao {
    List<TbUserLinkmanInfo> get(TbUserLinkmanInfo tbUserLinkmanInfo);

    TbUserLinkmanInfo find(String id);

    int insert(TbUserLinkmanInfo tbUserLinkmanInfo);

    int update(TbUserLinkmanInfo tbUserLinkmanInfo);

    int delete(String id);

    @Update("update TB_USER_LINKMAN_INFO set RELATION = #{relation},REALNAME = #{realname},MOBILE = #{mobile},SITUATION = #{situation}  WHERE ID = #{id}")
    void updateUserLinkmanInfo(TbUserLinkmanInfo tbUserLinkmanInfo);
}
