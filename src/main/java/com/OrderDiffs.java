package com;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
@Component
public class OrderDiffs {

        @JsonProperty("e")
        private String e;
        @JsonProperty("E")
        private long E;
        @JsonProperty ("s")
        private String s;
        @JsonProperty ("U")
        private long U;
        @JsonProperty ("u")
        private long u;
        @JsonProperty ("b")
        private double [] b;
        @JsonProperty ("a")
        private double [] a;

}
