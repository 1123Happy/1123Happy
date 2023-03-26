package com.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class OrderBook {

    private long lastUpdateId;
    private List<BidAsk> bids;
    private List<BidAsk> asks;

    public long getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public List<BidAsk> getBids() {
        return bids;
    }

    public void setBids(List<BidAsk> bids) {
        this.bids = bids;
    }

    public List<BidAsk> getAsks() {
        return asks;
    }

    public void setAsks(List<BidAsk> asks) {
        this.asks = asks;
    }

    public static class BidAsk {
        private String price;
        private String qty;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }
    }
}
