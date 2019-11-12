package learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@ToString(exclude = {"productCategory", "supplier", "orders", "stocks"})
@EqualsAndHashCode(exclude = {"productCategory", "supplier", "orders"})
@Entity(name = "Product")
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {

    @Id
    @Column(updatable = false, name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "Name")
    private String name;

    @NonNull
    @Column(nullable = false, name = "Description")
    private String description;

    @NonNull
    @Column(nullable = false, name = "Price")
    private BigDecimal price;

    @NonNull
    @Column(nullable = false, name = "Weight")
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "productCategoryId", referencedColumnName = "ProductCategoryID")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "SupplierID")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orders;

    @NonNull
    @Column(nullable = false, name = "ImageUrl")
    @JsonIgnore
    private String imageURL;

}

