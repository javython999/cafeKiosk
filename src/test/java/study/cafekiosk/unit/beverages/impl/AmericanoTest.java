package study.cafekiosk.unit.beverages.impl;

import org.junit.jupiter.api.Test;
import study.cafekiosk.unit.beverages.Beverage;

import static org.assertj.core.api.Assertions.assertThat;

class AmericanoTest {

    @Test
    void getName() {
        Beverage americano = new Americano();
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice() {
        Beverage americano = new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}