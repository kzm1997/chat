package com.kzm.chat.protocal.friend.dto;

public class UserDto {
    
    private String userId;  //用户id
    private String userNickName; //用户昵称
    private String userHead; //用户头像
    private Integer status; //状态 0添加 1保留 2.已添加

    public UserDto() {
    }

    public UserDto(String userId, String userNickName, String userHead, Integer status) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
