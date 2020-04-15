package com.bootdo.Check.Service;

import com.bootdo.Check.domain.TbChackRecord;
import com.bootdo.Check.domain.dto.TbChackDto;

import java.util.List;
import java.util.Map;

public interface ITbChackRecordServer {
    List<TbChackRecord> get(TbChackRecord tbUserLinkmanInfo);

    TbChackRecord find(String id);

    TbChackRecord queryTbChackRecord(Map<String,Object> hashMap);

    int insert(TbChackRecord tbUserLinkmanInfo) throws Exception;

    int update(TbChackRecord tbUserLinkmanInfo) throws Exception;

    int delete(String id) throws Exception;

    boolean saveTbChackRecord(TbChackDto tbChackDto) throws Exception;

}
