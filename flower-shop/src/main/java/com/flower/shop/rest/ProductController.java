package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ProductService;
import com.flower.shop.application.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.flower.shop.data.models.Product;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Void> addProduct( @Valid @RequestBody ProductDto product) {
        if(product.getPrice() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> removeProduct(@PathVariable String productID) {
        UUID ID = UUID.fromString(productID);
        if(productService.findProduct(ID).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productService.removeProduct(ID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
