package com.example.StockMarketTracker.Entity;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Entity
@Table(name = "tracked_symbols")
public class TrackedSymbols {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    private String type;
    private String coinGeckoId;
    private BigDecimal threshold;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCoinGeckoId() { return coinGeckoId; }
    public void setCoinGeckoId(String coinGeckoId) { this.coinGeckoId = coinGeckoId; }

    public BigDecimal getThreshold() { return threshold; }
    public void setThreshold(BigDecimal threshold) { this.threshold = threshold; }
}
