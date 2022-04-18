package com.kzm.chat.client.infrastructure.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanUtil {

    private static Map<String,Object> cachenMap=new ConcurrentHashMap<String, Object>();

    public static  void  addBean(String name,Object obj){
        cachenMap.put(name,obj);
    }

    public static <T> T getBean(String name,Class<T> t){
        return (T) cachenMap.get(name);
    }
}
