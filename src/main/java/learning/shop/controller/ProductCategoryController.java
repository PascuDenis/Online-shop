package learning.shop.controller;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import learning.shop.dto.ProductCategoryDTO;
import learning.shop.exception.ProductCartegoryNotFoundException;
import learning.shop.service.ProductCategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/productCategory")
public class ProductCategoryController implements IController<ProductCategoryDTO, Integer> {
  private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Autowired
    private final ProductCategoryService productCatrgoryService;

    @Override
    @GetMapping("/categories/{id}")
    public ResponseEntity<ProductCategoryDTO> getOne(@PathVariable Integer id) {
      try {
        return new ResponseEntity<>(productCatrgoryService.findOne(id), HttpStatus.OK);
      } catch (ProductCartegoryNotFoundException e) {
        logger.info(e);
      }
      return null;
    }

    @Override
    @GetMapping("/categories")
    public List<ProductCategoryDTO> getAll() {
        return productCatrgoryService.findAll();
    }
    @Override
    public ProductCategoryDTO save(ProductCategoryDTO entity) {
        return null;
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {
    }
}
