package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.application.dto.mapper.ProductMapper;
import com.flower.shop.data.dao.ProductDAO;
import com.flower.shop.data.models.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<ProductDto> getProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();
        List<ProductDto> result= listOfProducts.stream().
                map(p -> productMapper.mapProduct(p)).
                collect(Collectors.toList());

        return result;
    }

    public void createProduct(ProductDto product){
        Product newProduct = new Product(product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getPrice());
        productRepository.save(newProduct);
    }
}
