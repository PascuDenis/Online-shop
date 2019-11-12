package learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode(exclude = "products")
@NoArgsConstructor
@Entity(name = "Supplier")
@Table(name = "Supplier")
public class Supplier implements Serializable {
    @Id
    @Column(updatable = false, name = "SupplierID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "Name")
    private String name;

    @NonNull
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<Product> products;


    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}