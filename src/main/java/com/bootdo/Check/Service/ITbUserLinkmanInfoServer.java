package com.bootdo.Check.Service;

import com.bootdo.Check.domain.TbUserLinkmanInfo;

import java.util.List;

public interface ITbUserLinkmanInfoServer {

    List<TbUserLinkmanInfo> get(TbUserLinkmanInfo tbUserLinkmanInfo);

    TbUserLinkmanInfo find(String id);

    int insert(TbUserLinkmanInfo tbUserLinkmanInfo) throws Exception;

    int update(TbUserLinkmanInfo tbUserLinkmanInfo)throws Exception;

    int delete(String id)throws Exception;

    void updateUserLinkmanInfo(TbUserLinkmanInfo tbUserLinkmanInfo);
}
