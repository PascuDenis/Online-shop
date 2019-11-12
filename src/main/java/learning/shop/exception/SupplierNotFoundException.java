package learning.shop.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Integer id){
        super("Could not found supplier with id: " + id);
    }
}
