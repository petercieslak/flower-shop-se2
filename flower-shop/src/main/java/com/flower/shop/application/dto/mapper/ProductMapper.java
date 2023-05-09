package com.flower.shop.application.dto.mapper;

import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.data.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto mapProduct(Product product) {
        return new ProductDto(product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getPrice(),
                product.getFlowerType());
    }

}
