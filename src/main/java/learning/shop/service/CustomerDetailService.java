//package ro.msg.learning.shop.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import Customer;
//import ICustomerRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class CustomerDetailService implements UserDetailsService {
//  private final ICustomerRepository customerRepository;
//
//  @Bean
//  private BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Override
//  public UserDetails loadUserByUsername(String username) {
//    BCryptPasswordEncoder encoder = passwordEncoder();
//    Customer customer = customerRepository.findCustomerAfterUsername(username).orElse(null);
//    System.out.println(customer);
//
//    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//    grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
//
//    return new org.springframework.security.core.userdetails.User(customer.getUsername(),encoder.encode(customer.getPassword()),grantedAuthorityList);
//  }
//}
