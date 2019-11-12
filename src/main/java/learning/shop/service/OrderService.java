package learning.shop.service;

import learning.shop.dto.OrderDTO;
import learning.shop.dto.StockDTO;
import learning.shop.dto.orderinput.OrderInputDTO;
import learning.shop.exception.FailedToCreateOrderProductException;
import learning.shop.exception.FailedToCreateOrderStockException;
import learning.shop.exception.ProductNotFoundException;
import learning.shop.exception.StockNotFoundException;
import learning.shop.mapping.OrderMapper;
import learning.shop.model.Address;
import learning.shop.model.Order;
import learning.shop.model.OrderDetail;
import learning.shop.model.Stock;
import learning.shop.repository.*;
import learning.shop.service.strategy.FindLocationStrategy;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
@AllArgsConstructor
public class OrderService implements IService<OrderDTO, Integer> {

  private final IAddressRepository addressRepository;
  private final ICustomerRepository customerRepository;
  private final ILocationRepository locationRepository;
  private final IOrderRepository orderRepository;
  private final IStockRepository stockRepository;
  private final IProductRepository productRepository;
  private final IOrderDetailRepository orderDetailRepository;

  private FindLocationStrategy strategy;

  private static final Logger logger = LogManager.getLogger(OrderService.class.getName());

  @Override
  @Transactional
  public OrderDTO findOne(Integer id) {
    return new OrderMapper(addressRepository, customerRepository, locationRepository).convertToDto(orderRepository.findById(id).get());
  }

  @Override
  @Transactional
  public List<OrderDTO> findAll() {
    OrderMapper mapper = new OrderMapper(addressRepository, customerRepository, locationRepository);
    List<Order> orderList = (List<Order>) orderRepository.findAll();
    List<OrderDTO> orderReturnList = new ArrayList<>();
    for (Order order : orderList) {
      orderReturnList.add(mapper.convertToDto(order));
    }
    return orderReturnList;
//        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(mapper::convertToDto).
//                collect(Collectors.toList());
  }

  @Override
  @Transactional
  public OrderDTO save(OrderDTO entity) {
    return null;
  }

  @Override
  @Transactional
  public OrderDTO update(OrderDTO entity) {
    return null;
  }

  @Override
  @Transactional
  public void remove(Integer id) {

  }

  @Transactional
  public Order createOrder(OrderInputDTO inputOrder) throws IOException, MessagingException {
    for (Stock stock : stockRepository.findAll()) {
      logger.info(stock);
    }

    int updatedStock = 0;
    final Integer[] productId = {-1};
    List<StockDTO> searchedProducts = new ArrayList<>();
    try {
      searchedProducts = strategy.searchLocation(inputOrder);
    } catch (StockNotFoundException e) {
      System.out.println(e);
      throw new FailedToCreateOrderStockException();
    } catch (ProductNotFoundException e) {
      System.out.println(e);
      throw new FailedToCreateOrderProductException();
    }

    for (StockDTO s : searchedProducts) {
      System.out.println(s);
    }
    Address orderAddress = inputOrder.getAddress();
    Order order = null;
    try {
      order = new Order(
        LocalDateTime.now().withNano(0),
        searchedProducts.get(0).getLocation(),
        customerRepository.findById(1).get(),
        orderAddress
      );

      List<StockDTO> finalSearchedProducts = searchedProducts;

      inputOrder.getProductInputList().stream().forEach(product -> {
          finalSearchedProducts.stream().forEach(stock ->{
            if (stock.getProductId() != null) {
              productId[0] = stock.getProductId();
            } else if (stock.getProduct().getId() != null) {
              productId[0] = stock.getProduct().getId();
            }

            System.out.println("    " + product.getProductId());
            if (productId[0].equals(product.getProductId())) {
              int initialStock = stock.getQuantity();
              Integer stockIdAfterProductIdAndLocationId = stockRepository.findStockIdAfterProductIdAndLocationId(product.getProductId(), stock.getLocation().getId());
              Stock foundStock = stockRepository.findById(stockIdAfterProductIdAndLocationId).get();
              System.out.println(foundStock);
              foundStock.setQuantity(foundStock.getQuantity() - initialStock);
              stockRepository.save(foundStock);
              System.out.println("initial Stock" + initialStock);
            }
          });
        });

      System.out.println(updatedStock);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Salut " + e.toString());
    }

    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setId(1);
    orderDetail.setOrder(order);
    orderDetail.setProduct(productRepository.findById(inputOrder.getProductInputList().get(0).getProductId()).get());
    orderDetail.setQuantity(inputOrder.getProductInputList().get(0).getQuantity());


    System.out.println("\n" + order);
    orderRepository.save(order);
    System.out.println("____________________________");
    orderDetailRepository.save(orderDetail);
    System.out.println("|||||||||||||||||||||||||||");
    sendmail(order);
    return order;
  }

  private void sendmail(Order order) throws MessagingException, IOException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("bla01254@gmail.com", "Blablabla");
      }
    });
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerRepository.findCustomerAfterUsername(order.getCustomer().getUsername()).get().getEmail()));
    msg.setSubject("Order placed successfully");
    msg.setContent("Your order details", "text/html");
    msg.setSentDate(new Date());

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent("Your order details" +
        "\nOder address: " + order.getCreatedAt() +
        "\nOder customer: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName() + ", " + order.getCustomer().getEmail() +
        "\nOder address: " + order.getAddress().getCountry() + ", " + order.getAddress().getCity() + ", " + order.getAddress().getCounty() + ", " + order.getAddress().getStreet()
      , "text/html");


    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);
//    MimeBodyPart attachPart = new MimeBodyPart();
//
//    attachPart.attachFile("C:\\Users\\pascud\\Downloads\\online-shop-PascuDenis-master\\shop\\src\\main\\resources\\online-shop.png");
//    multipart.addBodyPart(attachPart);
    msg.setContent(multipart);
    Transport.send(msg);
  }
}
