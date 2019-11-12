package learning.shop.strategy;

import learning.shop.model.Address;
import learning.shop.repository.ILocationRepository;
import learning.shop.repository.IProductRepository;
import learning.shop.repository.IStockRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import learning.shop.dto.StockDTO;
import learning.shop.dto.orderinput.OrderInputDTO;
import learning.shop.dto.orderinput.ProductOrderInputDTO;
import learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import learning.shop.service.strategy.FindLocationStrategy;
import learning.shop.service.strategy.SingleLocation;

import java.util.ArrayList;
import java.util.List;

public class SingleLocationTest {
  @Mock
  private IProductRepository productRepository;
  @Mock
  private ILocationRepository locationRepository;
  @Mock
  private IStockRepository stockRepository;


  private FindLocationStrategy strategy;


  private ProductOrderInputDTO inputProduct1;
  private ProductOrderInputDTO inputProduct2;
  private ProductOrderInputDTO inputProduct3;

  private List<ProductOrderInputDTO> inputProducts;

  private Address address1;

  @Before
  public void setUp() {
    inputProduct1 = new ProductOrderInputDTO(1, 20);
    inputProduct2 = new ProductOrderInputDTO(2, 20);
    inputProduct3 = new ProductOrderInputDTO(3, 20);

    inputProducts = new ArrayList<>();

    inputProducts.add(inputProduct1);
    inputProducts.add(inputProduct2);

    inputProducts.add(inputProduct3);

    address1 = new Address(1, "County01", "City01", "County01", "Street01");

    strategy = new SingleLocation(productRepository, locationRepository, stockRepository);
    System.out.println(stockRepository.findAll());
  }

  @Test
  public void singelLocationSuccess() {
    OrderInputDTO inputOrder = new OrderInputDTO(address1, inputProducts);

    List<StockDTO> stocks = strategy.searchLocation(inputOrder);
    Assertions.assertThat(stocks).isNotEqualTo(null);
    System.out.println("Successfully created an order with simple location strategy");
  }

  @Test
  public void singleLocationFail() {
    OrderInputDTO inputOrder = new OrderInputDTO(address1, inputProducts);
    try {
      strategy.searchLocation(inputOrder);
    } catch (StockNotFoundException e) {
      System.out.println("Failed to create an order with single location strategy");
    } catch (RuntimeException e) {
      System.out.println("Some other error occurred");
    }
  }
}
