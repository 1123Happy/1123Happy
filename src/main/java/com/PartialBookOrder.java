package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class PartialBookOrder {
    @JsonProperty("lastUpdateId")
    private long lastUpdateId;
    @JsonProperty ("bids")
    private double [][] bids;
    @JsonProperty ("asks")
    private double [][] asks;

    public long getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public double[][] getBids() {
        return bids;
    }

    public void setBids(double[][] bids) {
        this.bids = bids;
    }

    public double[][] getAsks() {
        return asks;
    }

    public void setAsks(double[][] asks) {
        this.asks = asks;
    }
}
