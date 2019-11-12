package learning.shop.mapping;

import learning.shop.repository.ILocationRepository;
import learning.shop.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import learning.shop.dto.StockDTO;
import learning.shop.model.Stock;

@Component
@AllArgsConstructor
public class StockMapper implements Mapper<Stock, StockDTO> {
    private ILocationRepository locationRepository;
    private IProductRepository productRepository;

    @Override
    public Stock convertToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setQuantity(dto.getQuantity());
        stock.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        stock.setProduct(productRepository.findById(dto.getProductId()).orElse(null));
        return stock;
    }

    @Override
    public StockDTO convertToDto(Stock entity) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(entity.getId());
        stockDTO.setQuantity(entity.getQuantity());
        stockDTO.setLocation(entity.getLocation());
        stockDTO.setProduct(entity.getProduct());
        return stockDTO;
    }
}
