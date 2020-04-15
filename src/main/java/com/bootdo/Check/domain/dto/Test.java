package com.bootdo.Check.domain.dto;

import com.bootdo.Check.domain.TbUserLinkmanInfo;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.StringUtils;
import net.sf.json.JSONArray;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<HashMap<String , Object>> hashMaps = new ArrayList<HashMap<String , Object>>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("key","sdfs");
        map.put("val","sdfs");
        hashMaps.add(map);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("key2","sdfs");
        map2.put("val2",null);
        hashMaps.add(map2);

        JSONArray jsonArray = JSONArray.fromObject(hashMaps);
        System.out.println(jsonArray);

        for (Map maps: hashMaps) {
            Iterator<Map.Entry<Integer,Integer>> entries = maps.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<Integer,Integer> entry = entries.next();
                if (entry.getValue()==null){
                    maps.put(entry.getKey(),"");
                }
            }
        }
        JSONArray jsonArraya = JSONArray.fromObject(hashMaps);
        System.out.println(jsonArraya);

    }
}
