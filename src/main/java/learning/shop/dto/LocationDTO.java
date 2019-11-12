package learning.shop.dto;

import learning.shop.model.Address;
import learning.shop.model.Order;
import learning.shop.model.Revenue;
import learning.shop.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Integer id;
    private String name;
    private Integer addressId;

    private Address address;
    private List<Order> orders;
    private List<Stock> stocks;
    private List<Revenue> revenues;
}
