package com.example.StockMarketTracker.DTO;

import java.math.BigDecimal;
import java.time.Instant;

public class SymbolDTO {

    private  String Symbol;
    private String AssetType;
    private BigDecimal price;
    private  Instant Timestamp;


    public SymbolDTO(){};
    public SymbolDTO(String Symbol, String AssetType, BigDecimal price, Instant Timestamp)
    {
        this.Symbol=Symbol;
        this.AssetType=AssetType;
        this.price=price;
        this.Timestamp=Timestamp;
    }

    public String getSymbol() {
        return Symbol;
    }

    public String getAssetType() {
        return AssetType;
    }

    public Instant getTimestamp() {
        return Timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setAssetType(String assetType) {
        AssetType = assetType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public void setTimestamp(Instant timestamp) {
        Timestamp = timestamp;
    }
}
