package com.bootdo.BlackList.Service;

import com.bootdo.BlackList.domain.AddBlackList;
import com.bootdo.BlackList.domain.Blacklist;

import java.util.List;
import java.util.Map;



public interface BlackListService {
	//根据条件查询所有
	List<Blacklist> findBlackListByCondition(Map<String,Object> map);

	//根据条件查询数据总条数
	Long findBlackListCountByCondition(Map<String,Object> map);


	//更改黑名单用户状态为有效或者无效
	void updateBlackListState(int userId,String state,String operateTime);

	//根据用户id获得黑名单认证状态
	String findStateByuserId(int userId);


	//添加黑名单
	void addBlackListInfo(AddBlackList addBlackList);


	void updateBlackListState1(AddBlackList addBlackList);

}
