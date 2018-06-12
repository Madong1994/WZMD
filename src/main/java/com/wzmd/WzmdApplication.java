package com.wzmd;

import com.wzmd.connect.NettyStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.wzmd")
@MapperScan("com.wzmd.web.dao")
public class WzmdApplication{

	public static void main(String[] args) {
		SpringApplication.run(WzmdApplication.class, args);
	}
}
