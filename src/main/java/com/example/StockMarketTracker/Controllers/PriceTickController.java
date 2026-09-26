package com.example.StockMarketTracker.Controllers;


import com.example.StockMarketTracker.DTO.PriceEvent;
import com.example.StockMarketTracker.DTO.SymbolDTO;
import com.example.StockMarketTracker.Entity.PriceTick;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import com.example.StockMarketTracker.Services.SymbolService;
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
    SymbolService symbolService;



    @GetMapping("/all")
    public List<SymbolDTO> getAll()
    {
        return symbolService.getAllSymbols();
    }

    @GetMapping("/{symbol}")
    public List<SymbolDTO> getBySymbol(@PathVariable String symbol)
    {
        return symbolService.getBySymbolName(symbol);
    }
}
