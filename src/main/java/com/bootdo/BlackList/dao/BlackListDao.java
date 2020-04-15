package com.bootdo.BlackList.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.BlackList.domain.AddBlackList;
import com.bootdo.BlackList.domain.Blacklist;
import org.apache.ibatis.annotations.Param;



/*
 * 黑名单
 * */
public interface BlackListDao {
	//根据条件查询所有
	List<Blacklist> findBlackListByCondition(Map<String,Object> map);

	//根据条件查询数据总条数
	Long findBlackListCountByCondition(Map<String,Object> map);

	//根据用户id获得黑名单认证状态
	String findStateByuserId(@Param("userId") int userId);

	//更改黑名单用户状态为有效或者无效
	void updateBlackListState(@Param("userId") int userId,@Param("state") String state,@Param("operateTime") String operateTime);
	void updateBlackListState1(AddBlackList addBlackList);
	//添加黑名单
	void addBlackListInfo(AddBlackList addBlackList);
}
