package com.example.StockMarketTracker.KAFKA;


import com.example.StockMarketTracker.DTO.PriceEvent;
import com.example.StockMarketTracker.Market.MarketDataClient;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PriceEventProducer {


    private final MarketDataClient marketDataClient;
    private final KafkaTemplate<String, PriceEvent> kafkaTemplate;


    public PriceEventProducer(MarketDataClient marketDataClient, KafkaTemplate<String, PriceEvent> kafkaTemplate)
    {
        this.marketDataClient=marketDataClient;
        this.kafkaTemplate=kafkaTemplate;
    }

    @Scheduled(fixedRate = 300000)
    public void publishPriceSymbol()
    {
        String symbol = "AAPL";
        PriceEvent StockEvent = marketDataClient.fetchQuoteSymbol(symbol);
        kafkaTemplate.send("price-ticks", symbol, StockEvent);
        System.out.println("Published the Price of Symbol: "+ symbol +"@ Price: "+ StockEvent.getPrice());

        String CryptoSymbol = "BTCUSD";
        PriceEvent CryptoEvent = marketDataClient.fetchCryptoPrice("bitcoin", CryptoSymbol);
        kafkaTemplate.send("price-ticks", CryptoSymbol, CryptoEvent);
        System.out.println("Published the Price of Symbol: "+ symbol +"@ Price: "+ CryptoEvent.getPrice());
    }

}
