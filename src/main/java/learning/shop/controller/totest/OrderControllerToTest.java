package learning.shop.controller.totest;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(value = "test")
@RequestMapping("/order/test")
public class OrderControllerToTest {

//    private Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:test", "sa", "").load();
//
//    @GetMapping("/populate")
//    public void populate() {
//        flyway.migrate();
//    }
//
//    @GetMapping("/clear")
//    public void clear() {
//        flyway.clean();
//    }
}