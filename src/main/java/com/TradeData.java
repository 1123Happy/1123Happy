package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class TradeData {
    @JsonProperty("e")
    private String e;
    @JsonProperty("E")
    private long E;
    @JsonProperty ("s")
    private String s;
    @JsonProperty ("t")
    private long t;
    @JsonProperty ("p")
    private double p;
    @JsonProperty ("q")
    private double q;
    @JsonProperty ("b")
    private long b;
    @JsonProperty ("a")
    private long a;
    @JsonProperty ("T")
    private long T;
    @JsonProperty ("m")
    private boolean m;
    @JsonProperty ("M")
    private boolean M;

    public boolean isM() {
        return m;
    }

    public double getQ() {
        return q;
    }
}







