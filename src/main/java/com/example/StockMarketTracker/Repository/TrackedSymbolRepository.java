package com.example.StockMarketTracker.Repository;

import com.example.StockMarketTracker.Entity.TrackedSymbols;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackedSymbolRepository extends JpaRepository<TrackedSymbols, Long> {

    Optional <TrackedSymbols> getBySymbol(String symbol);

    boolean existBySymbol(String symbol);
}
