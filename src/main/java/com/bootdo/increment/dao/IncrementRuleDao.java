package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbIncrementParameter;
import com.bootdo.increment.domain.TbIncrementRule;

import java.util.List;
import java.util.Map;

/**
 * 增值产品规则
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-14 15:51
 **/
public interface IncrementRuleDao {

    /**
     * 增值产品规则查询
     *
     * @param incrementRule 查询条件
     *                      ruleName 规则名称
     *                      channalId渠道名称 01嘉福 02嘉薪
     * @return
     */
    List<TbIncrementRule> selectIncrementRuleList(TbIncrementRule incrementRule);

    /**
     * 增值产品规则查询count
     *
     * @param incrementRule 查询条件
     *                      ruleName 规则名称
     *                      channalId渠道名称 01嘉福 02嘉薪
     * @return
     */
    int selectIncrementRuleCount(TbIncrementRule incrementRule);

    /**
     * 个人增值持有限额和增值产品总限额查询
     * @return
     */
    TbIncrementParameter selectIncrementParameter();
}
