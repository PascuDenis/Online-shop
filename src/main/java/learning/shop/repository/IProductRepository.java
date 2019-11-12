package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Product;

public interface IProductRepository extends CrudRepository<Product, Integer> {
}
