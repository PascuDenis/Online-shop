package learning.shop.controller;

import learning.shop.dto.OrderDTO;
import learning.shop.dto.orderinput.OrderInputDTO;
import learning.shop.exception.FailedToCreateOrderProductException;
import learning.shop.exception.FailedToCreateOrderStockException;
import learning.shop.model.Order;
import learning.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.mail.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
class Filter2 extends OncePerRequestFilter {

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


@AllArgsConstructor
@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class OrderController implements IController<OrderDTO, Integer> {

  private final OrderService orderService;

  private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

  @Override
  @GetMapping("/orders/{id}")
  public ResponseEntity<OrderDTO> getOne(@PathVariable Integer id) {
    return new ResponseEntity<>(orderService.findOne(id), HttpStatus.OK);
  }

  @Override
  @GetMapping("/orders")
  public List<OrderDTO> getAll() {
    return orderService.findAll();
  }

  @Override
  public OrderDTO save(@RequestBody OrderDTO entity) {
    return null;
  }

  @PostMapping("/orders/s")
  public OrderDTO save(@RequestBody OrderInputDTO orderInputDTO) {
    return null;
  }

  @Override
  public OrderDTO update(OrderDTO entity) {
    return null;
  }

  @Override
  public void remove(Integer id) {

  }

  @PostMapping("/order")
  public ResponseEntity<Order> createOrder(@RequestBody OrderInputDTO order) throws IOException, MessagingException {
    System.out.println(order);
    try {
      return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    } catch (FailedToCreateOrderStockException e) {
      Order failedOrder = new Order();
      failedOrder.setId(-1);
      return new ResponseEntity<>(failedOrder, HttpStatus.NOT_FOUND);
    } catch (FailedToCreateOrderProductException e) {
      Order failedOrder = new Order();
      failedOrder.setId(-2);
      return new ResponseEntity<>(failedOrder, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user")
  public String loggedIn() {
    return "UserModel has loggeed in";
  }

}
