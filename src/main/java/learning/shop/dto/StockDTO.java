package learning.shop.dto;

import learning.shop.model.Location;
import learning.shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
  private Integer id;
  private Integer productId;
  private Integer quantity;
  private Integer locationId;

    public StockDTO(Integer productId, Integer quantity, Location location) {
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
    }

    private Product product;
    private Location location;

}
