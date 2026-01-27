package com.cloudacademy.stocks.service;

import com.cloudacademy.stocks.model.StockDTO;
import java.util.List;
import java.util.Optional;

public interface StockService {
    List<StockDTO> getAllStocks();
    Optional<StockDTO> getStockById(Long id);
    StockDTO saveStock(StockDTO employeeDTO);
    StockDTO updateStock(Long id, StockDTO employeeDTO);
    void deleteStock(Long id);
}
