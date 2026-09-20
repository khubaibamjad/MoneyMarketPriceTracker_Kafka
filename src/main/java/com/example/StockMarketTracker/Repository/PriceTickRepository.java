package com.example.StockMarketTracker.Repository;

import com.example.StockMarketTracker.Entity.AssetType;
import com.example.StockMarketTracker.Entity.PriceTick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface PriceTickRepository extends JpaRepository<PriceTick, Long> {

   List<PriceTick> findByAssetType(AssetType assetType);

   List<PriceTick> findBySymbol (String symbol);
}
