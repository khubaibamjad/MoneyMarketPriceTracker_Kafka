package com.example.StockMarketTracker.Config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "tracker")
public class WatchlistProperties {


    private List<WatchedAsset> watchedList;


    public List<WatchedAsset> getWatchedList()
    {
        return watchedList;
    }
    public void setWatchedList(List<WatchedAsset> watchedList)
    {
        this.watchedList=watchedList;
    }

    public static class WatchedAsset
    {
        private String Symbol;
        private String type;
        private String coinGeckoID;
        private BigDecimal thresholdId;

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String symbol) {
            Symbol = symbol;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCoinGeckoID() {
            return coinGeckoID;
        }

        public void setCoinGeckoID(String coinGeckoID) {
            this.coinGeckoID = coinGeckoID;
        }

        public BigDecimal getThresholdId() {
            return thresholdId;
        }

        public void setThresholdId(BigDecimal thresholdId) {
            this.thresholdId = thresholdId;
        }
    }
}
