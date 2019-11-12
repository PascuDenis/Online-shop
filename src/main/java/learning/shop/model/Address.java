package learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Address")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @Column(updatable = false, name = "AddressId")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "Country")
    private String country;

    @NonNull
    @Column(nullable = false, name = "City")
    private String city;

    @NonNull
    @Column(nullable = false, name = "County")
    private String county;

    @NonNull
    @Column(nullable = false, name = "Street")
    private String street;

    public Address(@NonNull String country, @NonNull String city, @NonNull String county, @NonNull String street) {
        this.country = country;
        this.city = city;
        this.county = county;
        this.street = street;
    }


}
