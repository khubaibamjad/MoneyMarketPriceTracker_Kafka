package com.example.StockMarketTracker.KAFKA;


import com.example.StockMarketTracker.DTO.PriceEvent;
import com.example.StockMarketTracker.Entity.AssetType;
import com.example.StockMarketTracker.Entity.PriceTick;
import com.example.StockMarketTracker.Repository.PriceTickRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PriceEventConsumer {

    private final PriceTickRepository priceTickRepository;

    public PriceEventConsumer(PriceTickRepository priceTickRepository)
    {
        this.priceTickRepository=priceTickRepository;
    }

    @KafkaListener(topics = "price-ticks", groupId = "stock-market-tracker")
    public void consume(PriceEvent event)
    {
        System.out.println("Recieved Event:"+ event.getSymbol() + " " + event.getPrice());
        PriceTick tick = new PriceTick(
                event.getSymbol(),
                AssetType.valueOf(event.getAssetType().toUpperCase()),
                event.getPrice(),
                event.getTimeStamp()
        );
        System.out.println("Price ticked of id saved: "+ tick.getId());
        priceTickRepository.save(tick);
    }
}
