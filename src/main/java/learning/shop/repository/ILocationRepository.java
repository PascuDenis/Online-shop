package learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Location;

public interface ILocationRepository extends CrudRepository<Location, Integer> {
}