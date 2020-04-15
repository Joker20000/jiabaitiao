package com.bootdo.Repayment.Service;
import com.bootdo.Repayment.domain.RepExcel;
import com.bootdo.Repayment.domain.Repay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRepayService {
    /**
     * lzp根据企业名称，时间，状态等查询信息
     * @param jiuyeTime
     * @return
     * cz
     */
    List<Repay> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<HashMap<String,Object>> ReExcel(Map<String,Object> map);
}
