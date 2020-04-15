package com.bootdo.BackerUser.Service;

import com.bootdo.BackerUser.domain.BackExcel;
import com.bootdo.BackerUser.domain.Enterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IEnterPriseService {
    /**
     * lzp根据企业名称，时间，状态等查询信息
     * @param
     * @return
     * cz
     */
    List<Enterprise> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<HashMap<String,Object>> BacExcel(Map<String,Object> map);
}
