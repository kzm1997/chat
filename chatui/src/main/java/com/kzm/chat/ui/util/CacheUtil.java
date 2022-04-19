package com.kzm.chat.ui.util;

import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElemntTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheUtil {

    // 对话框组
    public static Map<String, ElemntTalk> talkMap = new ConcurrentHashMap<String, ElemntTalk>(16);

}
