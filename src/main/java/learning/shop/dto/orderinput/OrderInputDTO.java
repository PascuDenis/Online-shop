package learning.shop.dto.orderinput;

import learning.shop.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderInputDTO {
//    private LocalDateTime timestamp;
    private Address address;
      private List<ProductOrderInputDTO> productInputList;
}
