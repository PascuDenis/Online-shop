package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.OrderDetail;

public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
}
