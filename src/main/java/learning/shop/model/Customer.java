package learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders"})
@Entity(name = "Customer")
@Table(name="Customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable//, UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "CustomerId")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "FirstName")
    private String firstName;

    @NonNull
    @Column(nullable = false, name = "LastName")
    private String lastName;

    @NonNull
    @Column(nullable = false, name = "Username")
    private String username;

    @NonNull
    @Column(nullable = false, name = "Password")
    private String password;

    @NonNull
    @Column(nullable = false, name = "EmailAddress")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    public Customer(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
