package com.example.StockMarketTracker.DTO;

import com.example.StockMarketTracker.Entity.PriceTick;

import java.math.BigDecimal;
import java.time.Instant;

public class PriceEvent {

    private String symbol;
    private String assetType;
    private BigDecimal price;
    private Instant timeStamp;

    public PriceEvent() {
    }

    ;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
