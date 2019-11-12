package learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "products")
@Entity(name = "ProductCategory")
@Table(name = "ProductCategory")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory implements Serializable {

    @Id
    @Column(updatable = false, name = "ProductCategoryID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private Set<Product> products;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCategory(Integer id,  String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
