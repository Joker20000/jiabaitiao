package com.bootdo.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Tools {

    /**
     * 将map进行排序
     * @param map
     * @return
     */
    public static String sortMapForAcc(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            if ("sign".equals(key) || "signature".equals(key)) {
                continue;
            }
            String new_value = map.get(key)+"";
            if (new_value != null && !"".equals(new_value)) {
                sb.append(key).append("=").append(new_value).append("&");
            }
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
