package com.service;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.entities.LearnBlock;
import com.entities.OrderBook;
import com.entities.ServerTime;
import com.entities.Trade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Service
@EnableScheduling
public class CollectorService {
    private final String symbol = "DOGEUSDT";
    private final SpotClient spotClient = new SpotClientImpl();

    private List<LearnBlock> rawBlocks = new ArrayList<>();

    private Deque<CompletableFuture> queue = new LinkedList<>();

    private final ThreadPoolExecutor executor;

    public CollectorService() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(16);
    }
    @Scheduled(fixedRate = 60000)
    private void fetchData() {
        LinkedHashMap<String, Object> paramsDepth = new LinkedHashMap<>();
        paramsDepth.put("symbol", this.symbol);
        paramsDepth.put("limit", 5000);
        String orderBookJSON = this.spotClient.createMarket().depth(paramsDepth);   //TODO parse
        String time = this.spotClient.createMarket().time();

        LinkedHashMap<String, Object> paramsTrades = new LinkedHashMap<>();
        paramsDepth.put("symbol", this.symbol);
        paramsDepth.put("limit", 1000);
        String tradesJSON = this.spotClient.createMarket().trades(paramsTrades);     //TODO parse

        this.queue.offerLast(CompletableFuture.<LearnBlock>supplyAsync(new Supplier<LearnBlock>() {

            @Override
            public LearnBlock get() {
                synchronized (queue) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        ServerTime serverTimeObj = objectMapper.readValue(time, ServerTime.class);
                        System.out.println("Server Time: " + serverTimeObj.getServerTime());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        List<Trade> trades = objectMapper.readValue(tradesJSON, new TypeReference<List<Trade>>() {
                        });
                        System.out.println("Number of trades: " + trades.size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        OrderBook orderBook = objectMapper.readValue(orderBookJSON, OrderBook.class);
                        System.out.println("Last Update ID: " + orderBook.getLastUpdateId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    return null;
                }
            }
        }));
    }

//    private LearnBlock parseData(String orderBookJSON, String paramsTrades, String time) {
//        return null;
//    }
}
