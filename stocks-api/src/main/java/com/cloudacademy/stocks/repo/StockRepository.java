package com.cloudacademy.stocks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudacademy.stocks.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
