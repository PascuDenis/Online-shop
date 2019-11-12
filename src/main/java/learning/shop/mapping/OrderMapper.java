package learning.shop.mapping;

import learning.shop.repository.IAddressRepository;
import learning.shop.repository.ICustomerRepository;
import learning.shop.repository.ILocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import learning.shop.dto.OrderDTO;
import learning.shop.model.Order;

@Component
@AllArgsConstructor
public class OrderMapper implements Mapper<Order, OrderDTO>{
    private IAddressRepository addressRepository;
    private ICustomerRepository customerRepository;
    private ILocationRepository locationRepository;

    @Override
    public Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        order.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
        order.setCreatedAt(dto.getCreatedAt());
        order.setAddress(addressRepository.findById(dto.getAddressId()).orElse(null));
        return order;
    }

    @Override
    public OrderDTO convertToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setLocation(entity.getLocation());
        orderDTO.setCustomer(entity.getCustomer());
        orderDTO.setCreatedAt(entity.getCreatedAt());
        orderDTO.setAddress(entity.getAddress());
        return orderDTO;
    }
}
