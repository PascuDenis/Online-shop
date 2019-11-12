package learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(exclude = {"order", "product"})
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable {

    @Id
    @Column(updatable = false, name = "OrderDetailId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ordersId", referencedColumnName = "OrdersId")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "ProductID")
    private Product product;

    @Column(nullable = false, name = "Quantity")
    private Integer quantity;

}
