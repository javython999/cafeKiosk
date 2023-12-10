package study.cafekiosk.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import study.cafekiosk.spring.IntegrationTestSupport;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StockTest extends IntegrationTestSupport {

    @Test
    @DisplayName("재고의 수량이 주문된 수량보다 작은지 확인한다.(true case)")
    void isQuantityLessThanTrue() {
        // given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        // when
        boolean result = stock.isQuantityLessThan(quantity);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("재고의 수량이 주문된 수량보다 작은지 확인한다.(false case)")
    void isQuantityLessThanFalse() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 2;

        // when
        boolean result = stock.isQuantityLessThan(quantity);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("재고를 주어진 개수만큼 차감 할 수 있다.")
    void deductQuantity() {
        // given
        Stock stock = Stock.create("001", 1);
        int quantity = 1;

        // when
        stock.deductQuantity(quantity);

        // then
        assertThat(stock.getQuantity()).isZero();
    }

    @Test
    @DisplayName("재고 보다 많은 개수를 차감 할 경우 예외가 발생한다.")
    void deductQuantityException() {
        // given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        // when // then
        assertThatThrownBy(() -> stock.deductQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 재고 수량이 없습니다.");
    }

    /*
    @DisplayName("")
    @TestFactory
    Collection<DynamicTest> dynamicTest() {
        return List.of(
            DynamicTest.dynamicTest("", () -> {

            }),
            DynamicTest.dynamicTest("", () -> {

            })
        );
    }
    */


    @DisplayName("재고 차감 시나리오")
    @TestFactory
    Collection<DynamicTest> stockDeducationDynamicTest() {
        // given
        Stock stock = Stock.create("001", 1);

        return List.of(
            DynamicTest.dynamicTest("재고를 주어진 개수만큼 차감할 수 있다.", () -> {
                // given
                int quantity = 1;

                // when
                stock.deductQuantity(quantity);

                // then
                assertThat(stock.getQuantity()).isZero();
            }),
            DynamicTest.dynamicTest("재고 보다 많은 수량으로 차감을 시도하는 경우 예외가 발생한다.", () -> {
                // given
                int quantity = 1;

                // when //then
                assertThatThrownBy(() -> stock.deductQuantity(quantity))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("차감할 재고 수량이 없습니다.");
            })
        );
    }

}