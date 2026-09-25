package com.example.StockMarketTracker.Market;


import com.example.StockMarketTracker.DTO.PriceEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.module.FindException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Component
public class MarketDataClient {


    private final RestClient restClient = RestClient.create();

    @Value("${finhub.Api-key}")
    private String finhubApiKey;

    public PriceEvent fetchQuoteSymbol (String symbol)
    {
        FinnhubQuote quote = restClient.get()
                .uri("https://finnhub.io/api/v1/quote?symbol={symbol}&token={token}", symbol, finhubApiKey)
                .retrieve()
                .body(FinnhubQuote.class);

        PriceEvent event = new PriceEvent();
        event.setSymbol(symbol);
        event.setAssetType("Stock");
        event.setPrice(BigDecimal.valueOf(quote.c()));
        event.setTimeStamp(Instant.now());

        return event;
    }


    @SuppressWarnings("unchecked")

    public PriceEvent fetchCryptoPrice(String coinGeckoId, String symbol)
    {
        Map<String, Map<String, Double>> response = restClient
                .get()
                .uri("https://api.coingecko.com/api/v3/simple/price?ids={id}&vs_currencies=usd", symbol)
                .retrieve()
                .body(Map.class);

        double priceCrypto = response.get(coinGeckoId).get("usd");


        PriceEvent event = new PriceEvent();
        event.setSymbol(symbol);
        event.setAssetType("Crypto");
        event.setPrice(BigDecimal.valueOf(priceCrypto));
        event.setTimeStamp(Instant.now());

        return event;
    }
    public record FinnhubQuote(double c, double h, double l, double o, double pc, long t){};
}
