package com.cloudacademy.stocks.dto;

import com.cloudacademy.stocks.entity.Stock;

import java.util.Date;

public record StockRecord(
        Long id,
        Date date,
        Double open,
        Double high,
        Double low,
        Double close,
        Integer volume) {

    public static StockRecord fromEntity(Stock stock) {
        if (stock == null) {
            return null;
        }

        return new StockRecord(
                stock.getId(),
                stock.getDate(),
                stock.getOpen(),
                stock.getHigh(),
                stock.getLow(),
                stock.getClose(),
                stock.getVolume());
    }

    public Stock toEntity() {
        return Stock.builder()
                .id(id)
                .date(date)
                .open(open)
                .high(high)
                .low(low)
                .close(close)
                .volume(volume)
                .build();
    }
}
