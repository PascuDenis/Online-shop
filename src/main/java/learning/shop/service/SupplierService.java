package learning.shop.service;

import learning.shop.exception.SupplierNotFoundException;
import learning.shop.repository.ISupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import learning.shop.dto.SupplierDTO;
import learning.shop.mapping.SupplierMapper;
import learning.shop.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SupplierService implements IService<SupplierDTO, Integer> {
    private final ISupplierRepository supplierRepository;

    @Override
    @Transactional
    public SupplierDTO findOne(Integer id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
        return new SupplierMapper().convertToDto(supplier);

    }

    @Override
    @Transactional
    public List<SupplierDTO> findAll() {

        SupplierMapper mapper = new SupplierMapper();
        return StreamSupport.stream(supplierRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SupplierDTO save(SupplierDTO entity) {
        Supplier supplier = supplierRepository.save(new SupplierMapper().convertToEntity(entity));
        return new SupplierMapper().convertToDto(supplier);
    }

    @Override
    @Transactional
    public SupplierDTO update(SupplierDTO entity) {
        Supplier supplierToUpdate = supplierRepository.findById(entity.getId()).orElseThrow(() -> new SupplierNotFoundException(entity.getId()));

        if (entity.getName() != null && !entity.getName().equals(supplierToUpdate.getName())) {
            supplierToUpdate.setName(entity.getName());
        }


        Supplier updatedSupplier = supplierRepository.save(supplierToUpdate);
        return new SupplierMapper().convertToDto(updatedSupplier);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        supplierRepository.deleteById(id);
    }
}
