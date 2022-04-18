package com.kzm.chat.codec;

import com.kzm.chat.protocal.Packet;
import com.kzm.chat.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder<Packet> {


    protected void encode(ChannelHandlerContext channelHandlerContext, Packet in, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(in);
        out.writeInt(data.length+1);
        out.writeByte(in.getCommand()); //添加指令
        out.writeBytes(data);
    }
}
