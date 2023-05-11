package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ProductService;
import com.flower.shop.application.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "type", defaultValue = "gift", required = true) String flowerType
    ) {
        List<ProductDto> products = productService.getProducts(pageNo, pageSize, flowerType);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<Void> addProduct( @Valid @RequestBody ProductDto product, @RequestHeader HttpHeaders headers) {
        if(product.getPrice() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> removeProduct(@PathVariable String productID) {
        UUID ID = UUID.fromString(productID);
        if(productService.findProduct(ID).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productService.removeProduct(ID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{productID}")
    public ResponseEntity<Void> modifyProduct(@Valid @RequestBody ProductDto product, @PathVariable String productID ) {
        UUID ID = UUID.fromString(productID);
        if(productService.findProduct(ID).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(product.getPrice() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        productService.modifyProduct(product, ID);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
