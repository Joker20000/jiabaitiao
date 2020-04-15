package com.bootdo.BlackList.ServiceImpl;

import java.util.List;
import java.util.Map;

import com.bootdo.BlackList.Service.BlackListService;
import com.bootdo.BlackList.dao.BlackListDao;
import com.bootdo.BlackList.domain.AddBlackList;
import com.bootdo.BlackList.domain.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("BlackListService")
public class BlackListServiceImpl  implements BlackListService {

	@Autowired
	private BlackListDao blackListdao;

	@Override
	public List<Blacklist> findBlackListByCondition(Map<String, Object> map) {
		return blackListdao.findBlackListByCondition(map);
	}

	@Override
	public Long findBlackListCountByCondition(Map<String, Object> map) {
		return blackListdao.findBlackListCountByCondition(map);
	}

	@Override
	public void updateBlackListState(int userId, String state,String operateTime) {
		blackListdao.updateBlackListState(userId, state, operateTime);
	}

	@Override
	public String findStateByuserId(int userId) {
		return blackListdao.findStateByuserId(userId);
	}

	@Override
	public void addBlackListInfo(AddBlackList addBlackList) {
		blackListdao.addBlackListInfo(addBlackList);
	}

	@Override
	public void updateBlackListState1(AddBlackList addBlackList) {
		blackListdao.updateBlackListState1(addBlackList);

	}




}
