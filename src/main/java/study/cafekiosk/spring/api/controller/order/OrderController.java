package study.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.cafekiosk.spring.api.controller.order.request.OrderCreateReqeust;
import study.cafekiosk.spring.api.controller.order.response.OrderResponse;
import study.cafekiosk.spring.api.service.OrderService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateReqeust reqeust) {
        LocalDateTime registeredTime = LocalDateTime.now();
        System.out.println(reqeust.toString());
        return orderService.createOrder(reqeust, registeredTime);
    }
}