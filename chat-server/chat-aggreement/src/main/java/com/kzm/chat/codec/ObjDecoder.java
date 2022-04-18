package com.kzm.chat.codec;

import com.kzm.chat.protocal.Packet;
import com.kzm.chat.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ObjDecoder extends ByteToMessageDecoder {


    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(1111);
       if (in.readableBytes()<4){
           return;
       }
       in.markReaderIndex(); //标记读位置
       int dataLength=in.readInt();
       if (in.readableBytes()<dataLength){
           in.resetReaderIndex();
           return;
       }
       byte command=in.readByte(); //读取指令
       byte[] data=new byte[dataLength-1]; //指令占用一位,剔除
       in.readBytes(data);
       out.add(SerializationUtil.deserialize(data, Packet.get(command))); //根据指令解析成不同的对象

    }
}
