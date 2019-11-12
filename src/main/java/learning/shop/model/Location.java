package learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Location")
@Table(name = "Location")
public class Location implements Serializable {
    @Id
    @Column(updatable = false, name = "LocationId")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "Name")
    private String name;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "AddressId")
    private Address address;

    public Location(@NonNull String name, @NonNull Address address) {
        this.name = name;
        this.address = address;
    }
}
