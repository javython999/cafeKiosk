package study.cafekiosk;

import org.junit.jupiter.api.Test;
import study.cafekiosk.unit.CafeKiosk;
import study.cafekiosk.unit.beverages.impl.Americano;


class CafeKioskApplicationTests {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>>> 주문 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>>> 주문 움료 : " + cafeKiosk.getBeverages().get(0).getName());
    }

}
