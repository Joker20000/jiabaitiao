package com.bootdo.Check.dao;

import com.bootdo.Check.domain.TbChackRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TbChackRecordDao {
    List<TbChackRecord> get(TbChackRecord tbUserLinkmanInfo);

    TbChackRecord find(String id);

    int insert(TbChackRecord tbUserLinkmanInfo);

    int update(TbChackRecord tbUserLinkmanInfo);

    int delete(String id);

    TbChackRecord queryTbChackRecord(Map<String,Object> hashMap);
}
