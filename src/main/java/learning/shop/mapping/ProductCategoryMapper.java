package learning.shop.mapping;

import learning.shop.model.ProductCategory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import learning.shop.dto.ProductCategoryDTO;

@Component
@AllArgsConstructor
public class ProductCategoryMapper implements Mapper<ProductCategory, ProductCategoryDTO> {

    @Override
    public ProductCategory convertToEntity(ProductCategoryDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, ProductCategory.class);
    }

    @Override
    public ProductCategoryDTO convertToDto(ProductCategory entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProductCategoryDTO.class);
    }
}
