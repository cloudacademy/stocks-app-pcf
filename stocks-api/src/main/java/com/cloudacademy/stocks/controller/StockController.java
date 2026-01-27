package com.cloudacademy.stocks.controller;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;

import org.springframework.http.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.info.BuildProperties;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import com.cloudacademy.stocks.service.StockService;
import com.cloudacademy.stocks.model.StockDTO;

@RestController
@RequestMapping("api/stocks")
public class StockController {

    private final StockService stockService;
    private final BuildProperties buildProperties;

    public StockController(StockService stockService, BuildProperties buildProperties) {
        this.stockService = stockService;
        this.buildProperties = buildProperties;
    }

    // http://localhost:8080/api/stocks/version
    @GetMapping(value = "/version", produces = "text/plain")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildProperties.getVersion());
    }

    // http://localhost:8080/api/stocks/ok
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/ok", produces = "text/plain")
    public ResponseEntity<String> getOk() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // http://localhost:8080/api/stocks
    @CrossOrigin(origins = "*")
    @GetMapping
    public List<StockDTO> getAllStocks() {
        return stockService.getAllStocks();
    }

    // http://localhost:8080/api/stocks
    @CrossOrigin(origins = "*")
    @PostMapping
    public StockDTO createEmployee(@RequestBody StockDTO stockDTO) {
        return stockService.saveStock(stockDTO);
    }

    // http://localhost:8080/api/stocks/csv
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<Resource> exportCSV() {
        // replace this with your header (if required)
        var csvHeader = new String[] {
                "date", "open", "high", "low", "close", "volume"
        };

        // replace this with your data retrieving logic
        var csvBody = new ArrayList<List<String>>();
        var stocks = stockService.getAllStocks();

        var dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        var numFormatter = new DecimalFormat("##.000000");

        stocks.forEach(stock -> {
            csvBody.add(Arrays.asList(
                    dateFormatter.format(stock.date()),
                    numFormatter.format(stock.open()),
                    numFormatter.format(stock.high()),
                    numFormatter.format(stock.low()),
                    numFormatter.format(stock.close()),
                    stock.volume().toString()));
        });

        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(csvHeader)
                .build();

        try (
                var out = new ByteArrayOutputStream();
                var printer = new CSVPrinter(new PrintWriter(out), csvFormat)) {
            csvBody.forEach(row -> {
                try {
                    printer.printRecord(row);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            printer.flush();

            var stream = new ByteArrayInputStream(out.toByteArray());

            var headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stocks.csv");
            headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

            return new ResponseEntity<>(
                    new InputStreamResource(stream),
                    headers,
                    HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}