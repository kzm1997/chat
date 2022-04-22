package com.example.chatserverboot.socket.handler;

import com.alibaba.fastjson.JSON;
import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.LuckUserInfo;
import com.example.chatserverboot.socket.MyBizHandler;
import com.kzm.chat.protocal.friend.SearchFriendRequest;
import com.kzm.chat.protocal.friend.dto.UserDto;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {
    
    public SearchFriendHandler(UserService userService){
        super(userService);
    }
    
    @Override
    public void mychannelRead(Channel channel, SearchFriendRequest msg) {
        log.info("搜索好友请求处理,{}", JSON.toJSONString(msg));

        List<UserDto> userDtoList=new ArrayList<>();
        List<LuckUserInfo> userInfoList=userService.queryFuzzyUserInfoList(msg.getUserId(),msg.getSearchKey());
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setStatus(userInfo.getStatus());
            userDtoList.add(userDto);
        }
        SearchFriendResponse response = new SearchFriendResponse();
        response.setList(userDtoList);
        channel.writeAndFlush(response);
    }
}
