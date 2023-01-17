package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class PartealBookOrder {
    @JsonProperty("lastUpdateId")
    private long lastUpdateId;
    @JsonProperty ("bids")
    private double [] bids;
    @JsonProperty ("asks")
    private double [] asks;
}
