package com.example.chatserverboot.infrastructure.common;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketChannelUtil {

    //用户
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<>();
    private static Map<String, String> userChannelId = new ConcurrentHashMap<>();

    public static void addChannel(String userId, Channel channel) {
        userChannel.put(userId, channel);
    }

    public static void removeChannel(String channelId) {
        String userId = userChannelId.get(channelId);
        if (userId == null) return;
        userChannel.remove(userId);
    }
    public static void removeUserChannelByUserId(String userId){
        userChannel.remove(userId);
    }

    public static Channel getChannel(String userId) {
        return userChannel.get(userId);
    }

}
