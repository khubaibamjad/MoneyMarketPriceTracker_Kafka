package com.example.StockMarketTracker.Services;


import com.example.StockMarketTracker.Controllers.PriceTickController;
import com.example.StockMarketTracker.DTO.SymbolDTO;
import com.example.StockMarketTracker.Entity.PriceTick;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymbolService {

    private final PriceTickRepository priceTickRepository;


    public SymbolService(PriceTickRepository priceTickRepository) {
        this.priceTickRepository = priceTickRepository;
    }

    private SymbolDTO convertToDTO(PriceTick priceTick) {
        return new SymbolDTO(
                priceTick.getSymbol()
                , priceTick.getAssetType().toString()
                , priceTick.getPrice()
                , priceTick.getTimestamp());
    }

    public List<SymbolDTO> getAllSymbols()
    {
        return priceTickRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<SymbolDTO> getBySymbolName(String symbol)
    {
        return priceTickRepository.findBySymbol(symbol)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
