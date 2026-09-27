package com.example.StockMarketTracker.Controllers;


import com.example.StockMarketTracker.Entity.TrackedSymbols;
import com.example.StockMarketTracker.Repository.TrackedSymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Api/Watchlist")
public class WatchListController {

    private final TrackedSymbolRepository trackedSymbolRepository;

    public WatchListController(TrackedSymbolRepository trackedSymbolRepository) {
        this.trackedSymbolRepository = trackedSymbolRepository;
    }

    @GetMapping
    public List<TrackedSymbols> getAllTrackedSymbols() {
        return trackedSymbolRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addTrackedSymbol(@RequestBody TrackedSymbols trackedSymbol) {
        if (trackedSymbolRepository.existBySymbol(trackedSymbol.getSymbol())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Symbol already tracked: " + trackedSymbol.getSymbol());
        }

        TrackedSymbols saved = trackedSymbolRepository.save(trackedSymbol);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{symbol}")
    public ResponseEntity<?> deleteTrackedSymbol(@PathVariable String symbol) {
        Optional<TrackedSymbols> existing = trackedSymbolRepository.getBySymbol(symbol);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        trackedSymbolRepository.delete(existing.get());
        return ResponseEntity.noContent().build();
    }
}
