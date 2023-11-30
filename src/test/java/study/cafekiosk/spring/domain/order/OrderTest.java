package study.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.cafekiosk.spring.domain.order.OrderStatus.*;
import static study.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static study.cafekiosk.spring.domain.product.ProductType.*;

class OrderTest {
    
    @Test
    @DisplayName("주문 생성시 상품 리스트에서 주문의 총 금액을 계산한다.")
    void calculateTotalPrice() {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());
        
        // then
        assertThat(order.getTotalPrice()).isEqualTo(4000);

    }


    @Test
    @DisplayName("주문 생성시 주문 상태는 INIT이다.")
    void init() {
        // given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(INIT);
    }

    @Test
    @DisplayName("주문 생성시 등록시간을 기록한다..")
    void registerdTime() {
        // given
        LocalDateTime registerdTime = LocalDateTime.now();

        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 3000)
        );

        // when
        Order order = Order.create(products, registerdTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registerdTime);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴이름")
                .build();
    }

}