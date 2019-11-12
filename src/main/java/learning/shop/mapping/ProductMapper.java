package learning.shop.mapping;

import learning.shop.repository.IProductCategoryRepository;
import learning.shop.repository.ISupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import learning.shop.dto.ProductDTO;
import learning.shop.model.Product;

@Component
@AllArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDTO> {
  private IProductCategoryRepository productCategoryRepository;
  private ISupplierRepository supplierRepository;


  @Override
  public Product convertToEntity(ProductDTO dto) {
    System.out.println(dto.getProductCategoryId());
    System.out.println(dto.getSupplierId());
    System.out.println(dto);

    Product product = new Product();
    product.setId(dto.getId());
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setWeight(dto.getWeight());

    product.setProductCategory(productCategoryRepository.findById(dto.getProductCategoryId()).orElse(null));
    product.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElse(null));
    return product;
  }

  @Override
  public ProductDTO convertToDto(Product entity) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(entity.getId());
    productDTO.setName(entity.getName());
    productDTO.setDescription(entity.getDescription());
    productDTO.setPrice(entity.getPrice());
    productDTO.setWeight(entity.getWeight());

    productDTO.setSupplierId(entity.getSupplier().getId());
    productDTO.setProductCategoryId(entity.getProductCategory().getId());

//    productDTO.setSupplier(entity.getSupplier());
//    productDTO.setProductCategory(entity.getProductCategory());
    return productDTO;
  }
}
