package com.example.StockMarketTracker;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class StockMarketTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketTrackerApplication.class, args);
	}


    @Bean
    public NewTopic priceTicksTopic()
    {
        return TopicBuilder.name("price-ticks")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
