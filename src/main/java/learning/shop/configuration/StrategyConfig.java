package learning.shop.configuration;

import learning.shop.repository.ILocationRepository;
import learning.shop.repository.IProductRepository;
import learning.shop.repository.IStockRepository;
import learning.shop.service.strategy.FindLocationStrategy;
import learning.shop.service.strategy.MostAbundantLocation;
import learning.shop.service.strategy.SingleLocation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StrategyConfig {
    public enum StrategyTypes {
        SINGLELOCATION, MOSTABUNDANTLOCATION
    }

    private final IProductRepository productRepository;
    private final ILocationRepository locationRepository;
    private final IStockRepository stockRepository;

    @Bean
    public FindLocationStrategy chooseStrategy(@Value("${strategy.type}") StrategyTypes strategy) {
        switch (strategy) {
            case SINGLELOCATION:
                return new SingleLocation(productRepository, locationRepository, stockRepository);
            case MOSTABUNDANTLOCATION:
                return new MostAbundantLocation(stockRepository);
        }
        return null;
    }
}
