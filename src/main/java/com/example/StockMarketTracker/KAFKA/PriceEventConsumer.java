package com.example.StockMarketTracker.KAFKA;

import com.example.StockMarketTracker.DTO.PriceEvent;
import com.example.StockMarketTracker.Entity.AssetType;
import com.example.StockMarketTracker.Entity.PriceTick;
import com.example.StockMarketTracker.Entity.TrackedSymbols;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import com.example.StockMarketTracker.Repository.TrackedSymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PriceEventConsumer {

    private final Logger logger = LoggerFactory.getLogger(PriceEventConsumer.class);
    private final PriceTickRepository priceTickRepository;
    private final TrackedSymbolRepository trackedSymbolRepository;

    public PriceEventConsumer(PriceTickRepository priceTickRepository, TrackedSymbolRepository trackedSymbolRepository)
    {
        this.priceTickRepository = priceTickRepository;
        this.trackedSymbolRepository = trackedSymbolRepository;
    }

    @KafkaListener(topics = "price-ticks", groupId = "stock-market-tracker")
    public void consume(PriceEvent event)
    {
        System.out.println("Recieved Event:" + event.getSymbol() + " " + event.getPrice());

        Optional<PriceTick> previousTick = priceTickRepository.findTopBySymbolOrderByTimeStampDesc(event.getSymbol());

        PriceTick tick = new PriceTick(
                event.getSymbol(),
                AssetType.valueOf(event.getAssetType().toUpperCase()),
                event.getPrice(),
                event.getTimeStamp()
        );
        priceTickRepository.save(tick);
        System.out.println("Price tick saved with id: " + tick.getId());

        Optional<TrackedSymbols> trackedSymbol = trackedSymbolRepository.getBySymbol(event.getSymbol());
        if (trackedSymbol.isPresent() && trackedSymbol.get().getThreshold() != null) {
            BigDecimal threshold = trackedSymbol.get().getThreshold();
            if (event.getPrice().compareTo(threshold) >= 0) {
                logger.warn("ALERT: {} is at or above its threshold of {} (current price: {})",
                        event.getSymbol(), threshold, event.getPrice());
            }
        }

        if (previousTick.isPresent()) {
            double oldPrice = previousTick.get().getPrice().doubleValue();
            double newPrice = event.getPrice().doubleValue();
            double percentChange = ((newPrice - oldPrice) / oldPrice) * 100;

            if (Math.abs(percentChange) >= 2.0) {
                logger.warn("ALERT: {} moved {}% since last check (from {} to {})",
                        event.getSymbol(), String.format("%.2f", percentChange), oldPrice, newPrice);
            }
        }
    }
}