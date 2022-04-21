package com.kzm.chat.protocal;

import com.kzm.chat.protocal.login.LoginRequest;
import com.kzm.chat.protocal.login.LoginResponse;
import com.kzm.chat.protocal.msg.MsgGroupRequest;
import com.kzm.chat.protocal.msg.MsgGroupResponse;
import com.kzm.chat.protocal.msg.MsgRequest;
import com.kzm.chat.protocal.msg.MsgResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Packet {

    private final static Map<Byte,Class<? extends Packet>> packetType=new ConcurrentHashMap<Byte, Class<? extends Packet>>();

    static {
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);
        packetType.put(Command.MsgRequest, MsgRequest.class);
        packetType.put(Command.MsgResponse, MsgResponse.class);
        packetType.put(Command.MsgGroupRequest, MsgGroupRequest.class);
        packetType.put(Command.MsgGroupResponse, MsgGroupResponse.class);
        
    }


    public static Class<? extends Packet> get(Byte command){
        return packetType.get(command);
    }

    /**
     * 获取协议指令
     * @return
     */
    public abstract Byte getCommand();


}
