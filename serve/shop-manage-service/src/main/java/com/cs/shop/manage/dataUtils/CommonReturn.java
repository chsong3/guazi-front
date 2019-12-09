package com.cs.shop.manage.dataUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: csong
 * @create: 2019-12-04
 **/
public class CommonReturn {
    public static Map ResultMessage(Object object,String message){
        Map map = new HashMap();
        map.put("map",object);
        map.put("message",message);
        return map;
    }
    public static Map ResultMessage2(Object object){
        Map map = new HashMap();
        map.put("map",object);
        return map;
    }
    public static Map ResultMessage3(String Message){
        Map map = new HashMap();
        map.put("map",Message);
        return map;
    }
}
