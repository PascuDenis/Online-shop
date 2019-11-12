package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Revenue;

//@Repository
//public interface IRevenueRepository extends JpaRepository<Revenue, Integer> {
//}

public interface IRevenueRepository extends CrudRepository<Revenue, Integer> {
}
