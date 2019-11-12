package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Address;

public interface IAddressRepository extends CrudRepository<Address, Integer> {
}

