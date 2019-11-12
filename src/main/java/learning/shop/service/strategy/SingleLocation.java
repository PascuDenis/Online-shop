package learning.shop.service.strategy;

import learning.shop.dto.StockDTO;
import learning.shop.exception.ProductInStockNotFoundException;
import learning.shop.exception.ProductNotFoundException;
import learning.shop.exception.StockNotFoundException;
import learning.shop.model.Location;
import learning.shop.model.Stock;
import learning.shop.repository.ILocationRepository;
import learning.shop.repository.IProductRepository;
import learning.shop.repository.IStockRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ro.msg.learning.shop.dto.*;
import learning.shop.dto.orderinput.OrderInputDTO;
import learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.*;
import learning.shop.mapping.StockMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import java.util.*;

@AllArgsConstructor
public class SingleLocation implements FindLocationStrategy {

  private static final Logger logger = LogManager.getLogger(SingleLocation.class.getName());

  private final IProductRepository productRepository;
  private final ILocationRepository locationRepository;
  private final IStockRepository stockRepository;

//  private List<StockDTO> getLocationsForOneProduct(Integer productId, Integer quantity) {
//    List<StockDTO> stockDTOS = new ArrayList<>();
//
//    stockDTOS = stockRepository.findStockLocationsForOneProduct(productId, quantity).stream().map(p ->
//      new StockMapper(locationRepository, productRepository).convertToDto(p)
//    ).collect(Collectors.toList());
//
//    for (Stock stock : stockRepository.findStockLocationsForOneProduct(productId, quantity)) {
//      stockDTOS.add(new StockMapper(locationRepository, productRepository).convertToDto(stock));
//    }
//    return stockDTOS;
//  }

  @Override
  public List<StockDTO> searchLocation(OrderInputDTO order) {
//TODO: optimize the method using Java8 streams -> ArrayList retainAll()
    boolean found = false;
    int countLocations = 0;
    List<StockDTO> productsStockToReturn = new ArrayList<>();
    Map<ProductOrderInputDTO, List<Location>> foundLocationsMAP = new HashMap<>();

    // Iterate through the order products and add every stock found in a list
    order.getProductInputList().stream().forEach(productOrderInputDTO -> {
      List<Location> foundLocations = new ArrayList<>();
      List<Stock> foundStocks = stockRepository.findStockLocationsForOneProduct(productOrderInputDTO.getProductId(), productOrderInputDTO.getQuantity());
      if (foundStocks.isEmpty()) {
        if (stockRepository.findStockForOneProductId(productOrderInputDTO.getProductId()).isEmpty()) {
          throw new ProductNotFoundException(productOrderInputDTO.getProductId());
        }
        throw new StockNotFoundException(productOrderInputDTO.getProductId());
      }
      for (Stock stock : foundStocks) {
        foundLocations.add(stock.getLocation());
      }
      foundLocationsMAP.put(productOrderInputDTO, foundLocations);
    });

    //Get the location list from the first product in the map
    Map.Entry<ProductOrderInputDTO, List<Location>> entry = foundLocationsMAP.entrySet().iterator().next();
    List<Location> locationsToBeSearched = entry.getValue();

    for (Location l : locationsToBeSearched) {
      //Iterate over these location list and check weather each product of the contains the searched location
      for (Map.Entry<ProductOrderInputDTO, List<Location>> locationEntry : foundLocationsMAP.entrySet()) {
        boolean goNext = false;
        // If the location list of a product doesn't contain the searched location throw error else increase a counter
        if (!locationEntry.getValue().contains(l)) {
          productsStockToReturn.clear();
          countLocations = 0;
          found = false;
          break;
        } else {
          countLocations++;
          for (Location stock : locationEntry.getValue()) {
            if (stock.getId().equals(l.getId())) {
              productsStockToReturn.add(new StockMapper(locationRepository, productRepository).convertToDto(stockRepository.findById(stock.getId()).get()));
              goNext = true;
            }
          }
          if (foundLocationsMAP.size() == countLocations) {
            found = true;
            break;
          }
        }
        if (goNext) break;
      }
      if (found) {
        break;
      }
    }
    if (productsStockToReturn.size() == foundLocationsMAP.size()) {
      return productsStockToReturn;
    } else {
      throw new ProductInStockNotFoundException();
    }
  }
}
