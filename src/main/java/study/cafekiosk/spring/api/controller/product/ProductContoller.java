package study.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.cafekiosk.spring.api.product.ProductService;
import study.cafekiosk.spring.api.product.response.ProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductContoller {

    private final ProductService productService;

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }
}
