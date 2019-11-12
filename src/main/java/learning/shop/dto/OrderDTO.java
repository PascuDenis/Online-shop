package learning.shop.dto;

import learning.shop.model.Address;
import learning.shop.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import learning.shop.model.Customer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer locationId;
    private Integer customerId;
    private LocalDateTime createdAt;
    private Integer addressId;

    private Location location;
    private Customer customer;
    private Address address;
}
