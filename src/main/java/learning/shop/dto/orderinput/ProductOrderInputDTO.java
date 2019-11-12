package learning.shop.dto.orderinput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderInputDTO {
    private Integer productId;
    private Integer quantity;
}
