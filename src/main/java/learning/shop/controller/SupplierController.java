package learning.shop.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import learning.shop.dto.SupplierDTO;
import learning.shop.exception.SupplierNotFoundException;
import learning.shop.service.SupplierService;

import java.util.List;

@RestController
public class SupplierController implements IController<SupplierDTO, Integer> {

    private final SupplierService supplierService;
    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    @GetMapping("/supplier/{id}")
    public ResponseEntity<SupplierDTO> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(supplierService.findOne(id), HttpStatus.OK);
        } catch (SupplierNotFoundException e) {
            logger.info(e);
        }
        return null;
    }

    @Override
    @GetMapping("/suppliers")
    public List<SupplierDTO> getAll() {
        return supplierService.findAll();
    }

    @Override
    @GetMapping("/supplier")
    public SupplierDTO save(SupplierDTO entity) {
        return null;
    }

    @Override
    @PutMapping("/supplier/{id}")
    public SupplierDTO update(SupplierDTO entity) {
        return null;
    }

    @Override
    @DeleteMapping("/supplier/{id}")
    public void remove(Integer id) {

    }
}
