package study.cafekiosk.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateReqeust {

    private List<String> productNumbers;

    @Builder
    public OrderCreateReqeust(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
