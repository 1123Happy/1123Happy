package com;

import com.binance.connector.client.WebsocketClient;
import com.binance.connector.client.impl.WebsocketClientImpl;
import com.binance.connector.client.impl.spot.Trade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/gettr")
public class BRestController {
    private WebsocketClient websocketClient=new WebsocketClientImpl();
    @GetMapping ("/getTradeA")
    public ResponseEntity getTradeAccumulation (int time, String tradeType) throws InterruptedException {
        double sum=0;
        ObjectMapper objectMapper = new ObjectMapper();
        List<TradeData> dataList=new ArrayList<>();
        int id = websocketClient.tradeStream("btcusdt",((i)->{
            System.out.println(i);
            try {
                TradeData data=objectMapper.readValue(i, TradeData.class);
                dataList.add(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }));
        Thread.sleep(time);
        websocketClient.closeConnection(id);
        double result= 0;
        for (int i=0; i<dataList.size();i++){
            if (tradeType.equals("BUY") && dataList.get(i).m==false){
                result=result+dataList.get(i).q;
            }
            else if (tradeType.equals("SELL") && dataList.get(i).m==true){
                result=result-dataList.get(i).q;
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
