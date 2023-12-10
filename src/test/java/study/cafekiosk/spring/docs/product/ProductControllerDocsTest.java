package study.cafekiosk.spring.docs.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import study.cafekiosk.spring.api.controller.product.ProductController;
import study.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import study.cafekiosk.spring.api.product.ProductService;
import study.cafekiosk.spring.api.product.response.ProductResponse;
import study.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import study.cafekiosk.spring.docs.RestDocsSupport;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static study.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static study.cafekiosk.spring.domain.product.ProductType.HANDMADE;

public class ProductControllerDocsTest extends RestDocsSupport {

    private final ProductService productService = mock(ProductService.class);

    @Override
    protected Object initController() {
        return new ProductController(productService);
    }

    @Test
    @DisplayName("신규 상품을 등록하는 API")
    void createProduct() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();


        given(productService.createProduct(any(ProductCreateServiceRequest.class)))
                .willReturn(
                    ProductResponse.builder()
                        .id(1L)
                        .productNumber("001")
                        .type(HANDMADE)
                        .sellingStatus(SELLING)
                        .name("아메리카노")
                        .price(4000)
                        .build()
                );


        // when  // then
        mockMvc.perform(
                    post("/api/v1/product/new")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                    document(
                        "product-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                            fieldWithPath("type").type(JsonFieldType.STRING).description("상품의 타입"),
                            fieldWithPath("sellingStatus").type(JsonFieldType.STRING).optional().description("상품의 판매상태"),
                            fieldWithPath("name").type(JsonFieldType.STRING).description("상품의 이름"),
                            fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품의 가격")
                        ),
                        responseFields(
                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                            fieldWithPath("status").type(JsonFieldType.STRING).description("응답 상태"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                            fieldWithPath("data").type(JsonFieldType.OBJECT).optional().description("응답 데이터"),
                            fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("상품의 ID"),
                            fieldWithPath("data.productNumber").type(JsonFieldType.STRING).description("상품의 제품번호"),
                            fieldWithPath("data.type").type(JsonFieldType.STRING).description("상품의 타입"),
                            fieldWithPath("data.sellingStatus").type(JsonFieldType.STRING).description("상품의 판매상태"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING).description("상품의 이름"),
                            fieldWithPath("data.price").type(JsonFieldType.NUMBER).description("상품의 가격")
                        )
                    )
                );

    }
}
