package com.cloudacademy.stocks.model;

import java.util.Date;

public record StockDTO(Long id, Date date, Double open, Double high, Double low, Double close, Integer volume) {}