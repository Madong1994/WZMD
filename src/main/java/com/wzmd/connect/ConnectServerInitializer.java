package com.wzmd.connect;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 马东 on 2018/3/28.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 11:50 2018/3/28
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
//@Service("connectServerInitializer")
public class ConnectServerInitializer extends ChannelInitializer<SocketChannel> {
//    @Autowired
//    ConnectServerHandler connectServerHandler;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("handler",new ConnectServerHandler());
//        pipeline.addLast("handler",connectServerHandler);

        System.out.println("client:" + socketChannel.remoteAddress() + "连接上");
    }
}
