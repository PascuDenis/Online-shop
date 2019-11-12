package learning.shop.service;

import learning.shop.exception.CustomerNotFoundException;
import learning.shop.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import learning.shop.dto.CustomerDTO;
import learning.shop.mapping.CustomerMapper;
import learning.shop.model.Customer;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements IService<CustomerDTO, Integer> {
    private final ICustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerDTO findOne(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        return new CustomerMapper().convertToDto(customer);
    }

    @Override
    @Transactional
    public List<CustomerDTO> findAll() {
        List customerList = (List) customerRepository.findAll();
        if (customerList.isEmpty()){
            throw new CustomerNotFoundException("Unable to find any customers");
        }
        return customerList;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO entity) {
        return null;
    }

    @Override
    @Transactional
    public void remove(Integer id) {

    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO entity) {
        return null;
    }

}
