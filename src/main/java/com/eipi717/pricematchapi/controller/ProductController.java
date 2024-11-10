package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.dtoConvertor.ProductWithPriceDTOConvertor;
import com.eipi717.pricematchapi.dtos.ProductDTO;
import com.eipi717.pricematchapi.dtos.ProductWithPriceDTO;
import com.eipi717.pricematchapi.entity.Price;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.PriceService;
import com.eipi717.pricematchapi.service.ProductService;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final PriceService priceService;

    @Autowired
    public ProductController(ProductService productService, PriceService priceService) {
        this.productService = productService;
        this.priceService = priceService;
    }

    @GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "sortBy", defaultValue = "productId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "item", defaultValue = "10") int item
    ) {
        SelfDefinedResponse<List<Product>> selfDefinedResponse = new SelfDefinedResponse();
        List<Product> productList = productService.getAllProduct(page, item, sortBy, orderBy).getContent();
        selfDefinedResponse.setData(productList);
        selfDefinedResponse.setCount(productList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getProductByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByName(
            @RequestParam String productName
    ) {
        SelfDefinedResponse<Product> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(productService.getProductByName(productName));

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getProductById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(
            @RequestParam Long productId
    ) {
        SelfDefinedResponse<Product> selfDefinedResponse = new SelfDefinedResponse();
        selfDefinedResponse.setData(productService.getProductById(productId));

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getProductByCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByCategory(
            @RequestParam String productCategory,
            @RequestParam(name = "sortBy", defaultValue = "productId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "item", defaultValue = "10") int item
    ) {
        SelfDefinedResponse<List<Product>> selfDefinedResponse = new SelfDefinedResponse();
        List<Product> productList = productService.getProductByCategory(page, item, productCategory, sortBy, orderBy);
        selfDefinedResponse.setData(productList);
        selfDefinedResponse.setCount(productList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/createProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(
            @RequestBody List<ProductDTO> productDTOList
    ) {
        productDTOList.forEach(productService::createProduct);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable Long productId
    ) {
        productService.deleteProductById(productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProductsWithPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProductsWithPrice(
            @RequestParam(name = "sortBy", defaultValue = "productId") String sortBy,
            @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "item", defaultValue = "10") int item
    ) {
        SelfDefinedResponse<List<ProductWithPriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        List<ProductWithPriceDTO> productWithPriceDTOList = new ArrayList<>();

        List<Product> productList = productService.getAllProduct(page, item, sortBy, orderBy).getContent();
        productList.forEach(product -> {
            List<Price> productCurrentPrice = priceService.getByProductId(product.getProductId());
            if (!productCurrentPrice.isEmpty()) {
                productWithPriceDTOList.add(ProductWithPriceDTOConvertor.convert(product, QueryUtils.getLatestPrice(productCurrentPrice)));
            } else {
                productWithPriceDTOList.add(ProductWithPriceDTOConvertor.convert(product, null));
            }
        });
        selfDefinedResponse.setData(productWithPriceDTOList);
        selfDefinedResponse.setCount(productList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/searchProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProduct(
            @RequestParam(name = "query", defaultValue = "") String query
    ) {
        SelfDefinedResponse<List<ProductWithPriceDTO>> selfDefinedResponse = new SelfDefinedResponse();
        List<ProductWithPriceDTO> productWithPriceDTOList = new ArrayList<>();

        List<Product> productList = productService.searchProduct(query);

        productList.forEach(product -> {
            List<Price> productCurrentPrice = priceService.getByProductId(product.getProductId());
            if (!productCurrentPrice.isEmpty()) {
                productWithPriceDTOList.add(ProductWithPriceDTOConvertor.convert(product, QueryUtils.getLatestPrice(productCurrentPrice)));
            } else {
                productWithPriceDTOList.add(ProductWithPriceDTOConvertor.convert(product, null));
            }
        });
        selfDefinedResponse.setData(productWithPriceDTOList);
        selfDefinedResponse.setCount(productList.size());
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }
}
