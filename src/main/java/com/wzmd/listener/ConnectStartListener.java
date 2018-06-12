package com.wzmd.listener;

import com.wzmd.connect.NettyStarter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;

/**
 * Created by 马东 on 2018/3/28.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 12:23 2018/3/28
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class ConnectStartListener implements SmartApplicationListener {
    private final static int BACHELOR = 110;

    private ApplicationContext applicationContext;

    private NettyStarter rpcServer;

    public ConnectStartListener(ApplicationContext applicationContext, NettyStarter rpcServer) {
        this.applicationContext = applicationContext;
        this.rpcServer = rpcServer;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == ContextRefreshedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return ApplicationContext.class.isAssignableFrom(aClass);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
//            rpcServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return BACHELOR;
    }
}
