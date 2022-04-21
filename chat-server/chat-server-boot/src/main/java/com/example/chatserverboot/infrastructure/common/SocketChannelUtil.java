package com.example.chatserverboot.infrastructure.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketChannelUtil {

    //用户
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<>();
    private static Map<String, String> userChannelId = new ConcurrentHashMap<>();
    //群组
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();


    public static void addChannel(String userId, Channel channel) {
        userChannel.put(userId, channel);
    }

    public synchronized static void removeChannel(String channelId) {
        String userId = userChannelId.get(channelId);
        if (userId == null) return;
        userChannel.remove(userId);
    }

    public static void removeUserChannelByUserId(String userId) {
        userChannel.remove(userId);
    }

    public static Channel getChannel(String userId) {
        return userChannel.get(userId);
    }


    //群组

    /**
     * 添加群组成员通信管道
     *
     * @param talkId
     * @param userChannel
     */
    public synchronized static ChannelGroup  addChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (null == channelGroup) {
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(talkId, channelGroup);
        }
        channelGroup.add(userChannel);
        return channelGroup;
    }

    public synchronized static void removeChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (channelGroup == null) return;
        channelGroupMap.remove(userChannel);
    }

    public static void removeChannelGroupByChannel(Channel userChannel){
        for (ChannelGroup channelGroup : channelGroupMap.values()) {
            channelGroup.remove(userChannel);
        }
    }

    /**
     * 获取群组通信管道
     * @param talkId
     * @return
     */
    public static ChannelGroup getChannelGroup(String talkId){
        return channelGroupMap.get(talkId);
    }

}
