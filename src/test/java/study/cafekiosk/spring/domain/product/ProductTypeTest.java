package study.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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

    @Test
    @DisplayName("모든 타입에 대해 재고 관련 타입인지 체크한다.")
    void containsStockTypeAllCase() {
        // given
        ProductType givenType1 = ProductType.BAKERY;
        ProductType givenType2 = ProductType.HANDMADE;
        ProductType givenType3 = ProductType.BOTTLE;

        // when
        boolean result1 = ProductType.containsStockType(givenType1);
        boolean result2 = ProductType.containsStockType(givenType2);
        boolean result3 = ProductType.containsStockType(givenType3);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isTrue();
    }


    @DisplayName("모든 타입에 대해 재고 관련 타입인지 체크한다.")
    @CsvSource({"HANDMADE,false", "BAKERY,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockTypeAllCase2(ProductType productType, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }


    private static Stream<Arguments> provideProductTypeForCheckingStockType() {
        return Stream.of(
                Arguments.of(ProductType.HANDMADE, false),
                Arguments.of(ProductType.BAKERY, true),
                Arguments.of(ProductType.BOTTLE, true)
        );
    }
    @DisplayName("모든 타입에 대해 재고 관련 타입인지 체크한다.")
    @MethodSource("provideProductTypeForCheckingStockType")
    @ParameterizedTest
    void containsStockTypeAllCase3(ProductType productType, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }


}