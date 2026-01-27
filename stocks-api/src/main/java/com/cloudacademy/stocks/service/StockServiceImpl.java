package com.cloudacademy.stocks.service;

import com.cloudacademy.stocks.model.Stock;
import com.cloudacademy.stocks.model.StockDTO;
import com.cloudacademy.stocks.repo.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StockDTO> getStockById(Long id) {
        return stockRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        Stock savedStock = stockRepository.save(stock);

        return convertToDTO(savedStock);
    }

    @Override
    public StockDTO updateStock(Long id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.setDate(stockDTO.date());
        stock.setOpen(stockDTO.open());
        stock.setHigh(stockDTO.high());
        stock.setLow(stockDTO.low());
        stock.setClose(stockDTO.close());
        stock.setVolume(stockDTO.volume());

        Stock updatedStock = stockRepository.save(stock);

        return convertToDTO(updatedStock);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    // Conversion methods between DTO and Entity
    private StockDTO convertToDTO(Stock stock) {
        return new StockDTO(stock.getId(),
                            stock.getDate(),
                            stock.getOpen(),
                            stock.getHigh(),
                            stock.getLow(),
                            stock.getClose(),
                            stock.getVolume());
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setDate(stockDTO.date());
        stock.setOpen(stockDTO.open());
        stock.setHigh(stockDTO.high());
        stock.setLow(stockDTO.low());
        stock.setClose(stockDTO.close());
        stock.setVolume(stockDTO.volume());
        return stock;
    }
}