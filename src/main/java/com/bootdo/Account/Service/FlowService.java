package com.bootdo.Account.Service;

import com.bootdo.Account.domain.Accounter;
import com.bootdo.Account.domain.FlowExcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FlowService {
    /**
     * lzp根据企业名称，时间，状态等查询信息
     * @param jiuyeTime
     * @return
     * cz
     */
    List<Accounter> list(Map<String, Object> map);

    int count(Map<String, Object> map);
    List<HashMap<String,Object>> FloExcel(Map<String,Object> map);
}
