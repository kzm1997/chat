package com.kzm.chat.protocal;

import com.kzm.chat.protocal.login.LoginRequest;
import com.kzm.chat.protocal.login.LoginResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Packet {

    private final static Map<Byte,Class<? extends Packet>> packetType=new ConcurrentHashMap<Byte, Class<? extends Packet>>();

    static {
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);
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
