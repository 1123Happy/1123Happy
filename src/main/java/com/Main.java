package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
class Main{
    public static void main(String[] args){
//        WebsocketClient websocketClient = new WebsocketClientImpl();
//        websocketClient.tradeStream("btcusdt",((i)->{
//            System.out.println(i);
//        }));
//        websocketClient.diffDepthStream("btcusdt", 1000, ((i)->{
//            System.out.println(i);
//        }));
        SpringApplication.run(Main.class,args);
    }
}