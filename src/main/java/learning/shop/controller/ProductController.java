package learning.shop.controller;

import learning.shop.dto.ProductDTO;
import learning.shop.exception.ProductNotFoundException;
import learning.shop.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
class Filter1 extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    response.setHeader("Cache-Control", "no-store"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.
    response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD"); // also added header to allow POST, GET method to be available
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Allow", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    filterChain.doFilter(request, response);
  }
}

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class ProductController implements IController<ProductDTO, Integer> {

    private final ProductService productService;
    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productService.findOne(id), HttpStatus.OK) ;
        } catch (ProductNotFoundException e) {
            logger.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @GetMapping("/products")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @Override
    @PostMapping("/product/save")
    public ProductDTO save(@RequestBody ProductDTO entity) {
        return productService.save(entity);
    }

    @Override
    @PutMapping("/product/update/{id}")
    public ProductDTO update(@RequestBody ProductDTO entity) {
        return productService.update(entity);
    }

    @Override
    @DeleteMapping("/product/remove/{id}")
    public void remove(@PathVariable Integer id) {
        productService.remove(id);
    }

    @RequestMapping("/")
    public String someTest(){
        System.out.println("Salutare");
        return "hahhaa";
    }
}
