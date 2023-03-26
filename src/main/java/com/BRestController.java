package com;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.WebsocketClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.impl.WebsocketClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping ("/gettr")
public class BRestController {
    private WebsocketClient websocketClient=new WebsocketClientImpl();
    private SpotClient spotClient = new SpotClientImpl();

    @CrossOrigin (origins = "*")
    @GetMapping ("/getTradeA")
    public ResponseEntity getTradeAccumulation (int time, String tradeType) throws InterruptedException {
        spotClient.createMarket().time();
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
            if (tradeType.equals("BUY") && dataList.get(i).isM()==false){
                result=result+dataList.get(i).getQ();
            }
            else if (tradeType.equals("SELL") && dataList.get(i).isM()==true){
                result=result-dataList.get(i).getQ();
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @CrossOrigin (origins = "*")
    @GetMapping ("/getOrderA")
    public ResponseEntity getTradeAccumulation (double p, String orderType) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        AtomicReference<PartialBookOrder> data = new AtomicReference<>();
        this.websocketClient.partialDepthStream("btcusdt", 20, 1000, ((r)->{
            try {
                data.set(objectMapper.readValue(r, PartialBookOrder.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }));
        if (orderType.equals("BUY")){
            return new ResponseEntity<>(Arrays.stream(data.get().getBids()).map(x->x[1]).mapToDouble(x->x).sum(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Arrays.stream(data.get().getAsks()).map(x->x[1]).mapToDouble(x->x).sum(), HttpStatus.OK);
    }
}
