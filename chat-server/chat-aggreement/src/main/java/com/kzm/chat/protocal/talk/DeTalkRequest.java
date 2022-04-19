package com.kzm.chat.protocal.talk;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;

public class DeTalkRequest extends Packet {
    
    private String userId; //用户Id
    private String talkId; //对话框Id

    public DeTalkRequest() {
    }

    public DeTalkRequest(String userId, String talkId) {
        this.userId = userId;
        this.talkId = talkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    @Override
    public Byte getCommand() {
        return Command.DelTalkRequest;
    }
}
