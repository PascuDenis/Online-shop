package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Order;

public interface IOrderRepository extends CrudRepository<Order, Integer> {
}
