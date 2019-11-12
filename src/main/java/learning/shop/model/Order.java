package learning.shop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString(exclude = {"customer", "address", "orderDetails"})
@EqualsAndHashCode(exclude = {"customer", "location", "address", "orderDetails"})
@Entity(name = "Order")
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "OrdersId")
    private Integer id;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ShippedFrom", referencedColumnName = "LocationId")
    private Location location;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "AddressId")
    private Address address;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    public Order(LocalDateTime createdAt, Location location, Customer customer, Address address) {
        this.createdAt = createdAt;
        this.location = location;
        this.customer = customer;
        this.address = address;
    }
}
