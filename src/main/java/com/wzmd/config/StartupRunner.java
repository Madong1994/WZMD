package com.wzmd.config;

import com.wzmd.connect.NettyStarter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by 马东 on 2018/3/28.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 13:05 2018/3/28
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        NettyStarter nettyStarter = NettyStarter.newInstance();
        nettyStarter.start();
    }
}
