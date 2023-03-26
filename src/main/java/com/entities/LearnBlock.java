package com.entities;

import lombok.Builder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Builder
public class LearnBlock {
    public static double PERCENT_DIFF_BOOK = 0.02;

    //order book sum order buy by level
    private double diff_order_b_1;

    private double diff_order_b_2;

    private double diff_order_b_3;

    private double diff_order_b_4;

    //order book sum order sell by level
    private double diff_order_s_1;

    private double diff_order_s_2;

    private double diff_order_s_3;

    private double diff_order_s_4;


    //buy sum trades buy
    private double buy_sum_current_min;

    private double buy_sum_1_min_ago;

    private double buy_sum_2_min_ago;

    private double buy_sum_3_min_ago;

    private double buy_sum_4_min_ago;

    private double buy_sum_5_min_ago;

    private double buy_sum_6_min_ago;

    private double buy_sum_7_min_ago;

    //buy sum trades sell
    private double sell_sum_current_min;

    private double sell_sum_1_min_ago;

    private double sell_sum_2_min_ago;

    private double sell_sum_3_min_ago;

    private double sell_sum_4_min_ago;

    private double sell_sum_5_min_ago;

    private double sell_sum_6_min_ago;

    private double sell_sum_7_min_ago;

    //prices weighted
    private double price_current;

    private double price_1_min_ago;

    private double price_2_min_ago;

    private double price_3_min_ago;

    private double price_4_min_ago;

    private double price_5_min_ago;

    private double price_6_min_ago;

    private double price_7_min_ago;


    private boolean is_ready = false;
}
