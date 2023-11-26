package study.cafekiosk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.cafekiosk.unit.CafeKiosk;
import study.cafekiosk.unit.beverages.Beverage;
import study.cafekiosk.unit.beverages.impl.Americano;
import study.cafekiosk.unit.beverages.impl.Latte;
import study.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class CafeKioskApplicationTests {

    @Test
    void manualOrderAdd() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>>> 주문 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>>> 주문 움료 : " + cafeKiosk.getBeverages().get(0).getName());
    }


    @DisplayName("음료 1개 추가하면 주문 목록에 담긴다.")
    @Test
    void orderAdd() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
        assertThat(cafeKiosk.getBeverages().get(0).getPrice()).isEqualTo(4000);
    }


    @Test
    void orderAddSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }


    @Test
    void orderAddZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }


    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }


    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();
        Beverage latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }


    @Test
    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    void calculateTotalPrice() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();
        Beverage latter = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latter);

        // when
        int totalPrice = cafeKiosk.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(8500);

    }


    @Test
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }


    @Test
    void createOrderCurrentTIme() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 11, 25, 10, 0));
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }


    @Test
    void createOrderOutsideOpenTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Beverage americano = new Americano();

        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2023, 11, 25, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 가능한 시간이 아닙니다. 관리자에게 문의하세요.");
    }


}
