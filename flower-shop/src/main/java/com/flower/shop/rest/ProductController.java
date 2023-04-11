package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ProductService;
import com.flower.shop.application.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addProduct( @Valid @RequestBody ProductDto product) {
        productService.createProduct(product);
    }
}
