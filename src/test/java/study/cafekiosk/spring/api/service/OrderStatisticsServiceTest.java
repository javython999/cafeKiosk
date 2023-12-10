package study.cafekiosk.spring.api.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import study.cafekiosk.spring.IntegrationTestSupport;
import study.cafekiosk.spring.client.mail.MailSendClient;
import study.cafekiosk.spring.domain.history.mail.MailSendHistory;
import study.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import study.cafekiosk.spring.domain.order.Order;
import study.cafekiosk.spring.domain.order.OrderRepository;
import study.cafekiosk.spring.domain.order.OrderStatus;
import study.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import study.cafekiosk.spring.domain.product.Product;
import study.cafekiosk.spring.domain.product.ProductRepository;
import study.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static study.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static study.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class OrderStatisticsServiceTest extends IntegrationTestSupport {

    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MailSendHistoryRepository mailSendHistoryRepository;



    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("결제 완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    void sendOrderStatisticsMail() {
        // given
        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        Order order1 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 12, 4, 23, 59, 59));
        Order order2 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 12, 5, 0, 0));
        Order order3 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 12, 6, 0, 0));
        Order order5 = createPaymentCompletedOrder(products, LocalDateTime.of(2023, 12, 5, 23, 59, 59));

        // stubbing
        when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);

        // when
        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023, 12, 5), "test@test.com");
        List<MailSendHistory> historys = mailSendHistoryRepository.findAll();

        // then
        assertThat(result).isTrue();

        assertThat(historys).hasSize(1)
                .extracting("content")
                .contains("총 매출 합계는 12000원입니다.");

        
        
    }

    private Order createPaymentCompletedOrder(List<Product> products, LocalDateTime orderDateTime) {
        Order order = Order.builder()
                .products(products)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .registeredDateTime(orderDateTime)
                .build();
        return orderRepository.save(order);
    }


    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴이름")
                .build();
    }

}