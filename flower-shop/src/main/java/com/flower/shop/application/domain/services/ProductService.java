package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.application.dto.mapper.ProductMapper;
import com.flower.shop.data.dao.ProductDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    @Autowired
    private ProductDAO productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> productMapper.mapProduct(p))
                .collect(Collectors.toList());
    }
}