package learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import learning.shop.model.Location;
import learning.shop.model.Stock;

import java.util.List;

public interface IStockRepository extends CrudRepository<Stock, Integer> {
    @Query("SELECT s FROM Stock s, Location l WHERE s.location.id = l.id AND s.product.id = ?1 AND s.quantity >= ?2")
    List<Stock> findStockLocationsForOneProduct(Integer productId, Integer quantity);

    @Query("SELECT s FROM Stock s, Location l WHERE s.location.id = l.id AND s.product.id = ?1")
    List<Stock> findStockForOneProductId(Integer productId);

    @Query("SELECT s.id FROM Stock s WHERE s.product.id = ?1 AND s.location.id = ?2")
    Integer findStockIdAfterProductIdAndLocationId(Integer productId, Integer locationId);

    @Query("SELECT s FROM Stock s WHERE s.location.id = ?1")
    Stock getStockForProductWithLocation(Integer locationId);

    @Query(
//            "SELECT s FROM Stock s, Location l WHERE " +
            "SELECT l FROM Location l, Stock s WHERE" +
            " s.location.id = l.id AND " +
            "s.product.id = ?1 AND " +
            "s.quantity >= ?2 AND " +
            "s.quantity = (" +
            "SELECT MAX(s2.quantity) FROM Stock s2 WHERE s.product.id = s2.product.id" +
            ")")
    Location getLocationWithMaximumQuantityForOneProduct(Integer productId, Integer quantity);
}
