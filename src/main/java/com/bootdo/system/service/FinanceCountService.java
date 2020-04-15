package com.bootdo.system.service;

import com.bootdo.system.domain.FinanceCountDO;

import java.util.List;
import java.util.Map;

/**
 * 财务统计表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-18 16:22:07
 */
public interface FinanceCountService {
	
	FinanceCountDO get(Integer countId);
	
	List<FinanceCountDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FinanceCountDO financeCount);
	
	int update(FinanceCountDO financeCount);
	
	int remove(Integer countId);
	
	int batchRemove(Integer[] countIds);
}
