package com.wzmd.connect;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by 马东 on 2018/3/28.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 11:53 2018/3/28
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
//@Service("connectServerHandler")
//@Scope("prototype")
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;
//@ChannelHandler.Sharable
public class ConnectServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            if (channel != incoming){
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
            } else {
                channel.writeAndFlush("[you]" + s + "\n");
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------handlerAdded-------");
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[server] - " + incoming.remoteAddress() + "加入\n");
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------handlerRemoved-------");
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[server] - " + incoming.remoteAddress() + "离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------channelActive-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "在线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------channelInactive-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "掉线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("-----------exceptionCaught-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "异常\n");
        cause.printStackTrace();
        ctx.close();
    }
}
