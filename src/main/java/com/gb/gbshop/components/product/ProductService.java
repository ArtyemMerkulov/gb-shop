package com.gb.gbshop.components.product;


import com.gb.gbshop.entity.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(UUID id);

    Product save(Product product);

    void delete(UUID id);

}
