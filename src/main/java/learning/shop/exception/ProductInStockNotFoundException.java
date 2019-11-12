package learning.shop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductInStockNotFoundException extends RuntimeException {
    public ProductInStockNotFoundException(Integer id) {
        super("Could not found product in stock with id: " + id);
    }
}
