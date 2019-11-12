package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Supplier;

public interface ISupplierRepository extends CrudRepository<Supplier, Integer> {
}

