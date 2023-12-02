package study.cafekiosk.spring.api.controller.product.dto.request;

import study.cafekiosk.spring.domain.product.ProductSellingStatus;
import study.cafekiosk.spring.domain.product.ProductType;

public class ProductCreateRequest {
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;
}
