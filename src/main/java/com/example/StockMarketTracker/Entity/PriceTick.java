package com.example.StockMarketTracker.Entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class PriceTick {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    private BigDecimal price;

    private Instant timeStamp;

    public PriceTick(){};

    public PriceTick(Long id, String symbol, AssetType assetType, BigDecimal price, Instant timestamp)
    {
        this.symbol=symbol;
        this.assetType=assetType;
        this.price=price;
        this.timeStamp=timestamp;
    }

    public PriceTick(String symbol, AssetType assetType, BigDecimal price, Instant timeStamp) {
        this.symbol=symbol;
        this.assetType=assetType;
        this.price=price;
        this.timeStamp=timeStamp;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getTimestamp() {
        return timeStamp;
    }
}
