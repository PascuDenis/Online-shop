package learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Customer;

import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer, Integer> {

  @Query("SELECT c FROM Customer c WHERE c.username = ?1 AND c.password = ?2")
  Customer findCustomerAfterUsernameAndPassword(String username, String password);

  @Query("SELECT c FROM Customer c WHERE c.username = ?1")
  Optional<Customer> findCustomerAfterUsername(String username);

}
