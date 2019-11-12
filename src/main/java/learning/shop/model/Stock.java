package learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@ToString(exclude = {"product", "location"})
@ToString
@EqualsAndHashCode(exclude = {"product", "location"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Stock")
public class Stock implements Serializable {

    @Id
    @Column(updatable = false, name = "StockId")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "ProductID")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", referencedColumnName = "LocationId")
    private Location location;

    @NonNull
    @Column(nullable = false, name = "Quantity")
    private Integer quantity;

}
