package com.bootdo.increment.service.impl;

import com.bootdo.increment.dao.IncrementRuleDao;
import com.bootdo.increment.domain.TbIncrementParameter;
import com.bootdo.increment.domain.TbIncrementRule;
import com.bootdo.increment.service.IncrementRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 增值规则实现类
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-14 15:59
 **/
@Service
public class IncrementRuleServcieImpl implements IncrementRuleService {
    @Autowired
    private IncrementRuleDao incrementRuleDao;

    /**
     *增值产品规则查询
     * @param incrementRule 查询条件
     *                      ruleName 规则名称
     *                      channalId渠道名称 01嘉福 02嘉薪
     * @return
     */
    @Override
    public List<TbIncrementRule> selectIncrementRuleList(TbIncrementRule incrementRule) {
        return incrementRuleDao.selectIncrementRuleList(incrementRule);
    }

    /**
     *增值产品规则查询 count
     * @param incrementRule 查询条件
     *                      ruleName 规则名称
     *                      channalId渠道名称 01嘉福 02嘉薪
     * @return
     */
    @Override
    public int selectIncrementRuleCount(TbIncrementRule incrementRule) {
        return incrementRuleDao.selectIncrementRuleCount(incrementRule);
    }


    /**
     * 个人增值持有限额和增值产品总限额查询
     * @return
     */
    @Override
    public TbIncrementParameter selectIncrementParameter() {
        return incrementRuleDao.selectIncrementParameter();
    }
}
