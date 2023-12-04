package study.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.cafekiosk.spring.api.ApiResponse;
import study.cafekiosk.spring.api.controller.order.request.OrderCreateReqeust;
import study.cafekiosk.spring.api.controller.order.response.OrderResponse;
import study.cafekiosk.spring.api.service.OrderService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateReqeust reqeust) {
        LocalDateTime registeredTime = LocalDateTime.now();
        return ApiResponse.ok(orderService.createOrder(reqeust.toServiceRequest(), registeredTime));
    }
}
