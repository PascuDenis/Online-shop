package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.ProductCategory;

public interface IProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
}
