package learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "Revenue")
@Table(name = "Revenue")
@NoArgsConstructor
@AllArgsConstructor

public class Revenue implements Serializable {

    @Id
    @Column(updatable = false, name = "RevenueId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "LocationId")
    private Location location;

    @NonNull
    @Column(name="Date_", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name="Sum_" , nullable = false)
    private BigDecimal sum;

    public Revenue(Location location, @NonNull LocalDate date, @NonNull BigDecimal sum) {
        this.location = location;
        this.date = date;
        this.sum = sum;
    }
}
