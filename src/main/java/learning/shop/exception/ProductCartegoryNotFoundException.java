package learning.shop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductCartegoryNotFoundException extends RuntimeException {
    public ProductCartegoryNotFoundException(Integer id) {
        super("Could not found product category with id: " + id);
    }
}
