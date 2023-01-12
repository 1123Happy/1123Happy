package com;

import com.binance.connector.client.WebsocketClient;
import com.binance.connector.client.impl.WebsocketClientImpl;

class Main{
    public static void main(String[] args){
        WebsocketClient websocketClient = new WebsocketClientImpl();
//        websocketClient.tradeStream("btcusdt",((i)->{
//            System.out.println(i);
//        }));
        websocketClient.diffDepthStream("btcusdt", 1000, ((i)->{
            System.out.println(i);
        }));
    }
}