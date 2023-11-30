package study.cafekiosk.spring.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cafekiosk.spring.api.controller.order.request.OrderCreateReqeust;
import study.cafekiosk.spring.api.controller.order.response.OrderResponse;
import study.cafekiosk.spring.domain.order.Order;
import study.cafekiosk.spring.domain.order.OrderRepository;
import study.cafekiosk.spring.domain.product.Product;
import study.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    public OrderResponse createOrder(OrderCreateReqeust reqeust, LocalDateTime registeredDateTime) {
        List<String> productNumbers = reqeust.getProductNumbers();

        // product
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        // order
        Order order = Order.create(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);
    }
}
