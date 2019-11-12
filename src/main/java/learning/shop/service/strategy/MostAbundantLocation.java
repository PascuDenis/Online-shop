package learning.shop.service.strategy;

import learning.shop.dto.StockDTO;
import learning.shop.exception.StockNotFoundException;
import learning.shop.model.Location;
import learning.shop.repository.IStockRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.*;
import learning.shop.dto.orderinput.OrderInputDTO;
import learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.model.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MostAbundantLocation implements FindLocationStrategy {
  private static final Logger logger = LogManager.getLogger(MostAbundantLocation.class.getName());

  private final IStockRepository stockRepository;

  private Location locationsWithMaxQuantityForOneProduct(ProductOrderInputDTO product) {
    Location productLocation = stockRepository.getLocationWithMaximumQuantityForOneProduct(product.getProductId(), product.getQuantity());
    if (productLocation == null) {
      throw new StockNotFoundException(product.getProductId());
    }
    return productLocation;
  }

  @Transactional
  @Override
  public List<StockDTO> searchLocation(OrderInputDTO order) {
    List<StockDTO> productsStockToReturn;

//        for(ProductOrderInputDTO product : order.getProductInputList()){
//            StockDTO foundStock = new StockDTO(product.getProductId(), product.getQuantity(), locationsWithMaxQuantityForOneProduct(product));
//            productsStockToReturn.add(foundStock);
//        }

    productsStockToReturn = order.getProductInputList().stream().map(product ->
      new StockDTO(product.getProductId(), product.getQuantity(), locationsWithMaxQuantityForOneProduct(product))
    ).collect(Collectors.toList());

    productsStockToReturn.forEach(p -> {
      System.out.println("TATTATATATATATAT" + p);
    });
    return productsStockToReturn;
  }
}
