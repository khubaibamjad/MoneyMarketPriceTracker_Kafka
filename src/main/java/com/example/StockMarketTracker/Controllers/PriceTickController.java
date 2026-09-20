package com.example.StockMarketTracker.Controllers;


import com.example.StockMarketTracker.DTO.PriceEvent;
import com.example.StockMarketTracker.Entity.PriceTick;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceTickController {

    @Autowired
    PriceTickRepository priceTickRepository;


    @GetMapping("/all")
    public List<PriceTick> getAll()
    {
        return priceTickRepository.findAll();
    }

    @GetMapping("/{symbol}")
    public List<PriceTick> getBySymbol(@PathVariable String symbol)
    {
        return priceTickRepository.findBySymbol(symbol.toUpperCase());
    }
}
