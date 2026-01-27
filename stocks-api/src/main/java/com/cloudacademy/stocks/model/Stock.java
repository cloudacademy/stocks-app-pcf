package com.cloudacademy.stocks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.util.Date;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Date date;
    @Column(nullable = false)
    private Double open;
    @Column(nullable = false)
    private Double high;
    @Column(nullable = false)
    private Double low;
    @Column(nullable = false)
    private Double close;
    @Column(nullable = false)
    private Integer volume;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Double getOpen() { return open; }
    public void setOpen(Double open) { this.open = open; }

    public Double getHigh() { return high; }
    public void setHigh(Double high) { this.high = high; }

    public Double getLow() { return low; }
    public void setLow(Double low) { this.low = low; }

    public Double getClose() { return close; }
    public void setClose(Double close) { this.close = close; }

    public Integer getVolume() { return volume; }
    public void setVolume(Integer volume) { this.volume = volume; }
}