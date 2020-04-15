package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.FinanceCountDao;
import com.bootdo.system.domain.FinanceCountDO;
import com.bootdo.system.service.FinanceCountService;



@Service
public class FinanceCountServiceImpl implements FinanceCountService {
	@Autowired
	private FinanceCountDao financeCountDao;
	
	@Override
	public FinanceCountDO get(Integer countId){
		return financeCountDao.get(countId);
	}
	
	@Override
	public List<FinanceCountDO> list(Map<String, Object> map){
		return financeCountDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return financeCountDao.count(map);
	}
	
	@Override
	public int save(FinanceCountDO financeCount){
		return financeCountDao.save(financeCount);
	}
	
	@Override
	public int update(FinanceCountDO financeCount){
		return financeCountDao.update(financeCount);
	}
	
	@Override
	public int remove(Integer countId){
		return financeCountDao.remove(countId);
	}
	
	@Override
	public int batchRemove(Integer[] countIds){
		return financeCountDao.batchRemove(countIds);
	}
	
}
