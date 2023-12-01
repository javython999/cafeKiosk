package study.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.(false case)")
    void containsStockTypeIsFalse() {
        // given
        ProductType givenType = ProductType.HANDMADE;

        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.(true case)")
    void containsStockTypeIsTrue() {
        // given
        ProductType givenType = ProductType.BAKERY;

        // when
        boolean result = ProductType.containsStockType(givenType);

        // then
        assertThat(result).isTrue();
    }

}