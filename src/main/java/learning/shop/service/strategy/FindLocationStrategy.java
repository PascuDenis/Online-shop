package learning.shop.service.strategy;

import org.springframework.stereotype.Component;
import learning.shop.dto.StockDTO;
import learning.shop.dto.orderinput.OrderInputDTO;

import java.util.List;


@Component
public interface FindLocationStrategy {
    List<StockDTO> searchLocation(OrderInputDTO order);
}
