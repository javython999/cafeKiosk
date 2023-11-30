package study.cafekiosk.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class OrderCreateReqeust {

    private List<String> productNumbers;

    @Builder
    public OrderCreateReqeust(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
