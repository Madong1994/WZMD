package com.wzmd.connect;

import com.wzmd.config.ConnectConfiguration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by 马东 on 2018/3/28.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 11:36 2018/3/28
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
//@Service("nettyStarter")
public class NettyStarter {
//    @Autowired
//    private ConnectServerInitializer connectServerInitializer;

    private static NettyStarter nettyStarter = null;

    public static NettyStarter newInstance() {
        if(nettyStarter == null ){
            synchronized(NettyStarter.class){
                if(nettyStarter == null){
                    nettyStarter = new NettyStarter();
                }
            }
        }
        return nettyStarter;
    }

    public void start(){
        if(nettyStarter != null){
            nettyStarter.run();
        }
    }

    //程序初始方法入口注解，提示spring这个程序先执行这里
//    @PostConstruct
    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                        .childHandler(new ConnectServerInitializer())
//                    .childHandler(connectServerInitializer)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            System.out.println("ServerMain启动了...");
            ChannelFuture f = b.bind(ConnectConfiguration.CNN_PORT).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
