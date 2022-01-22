package com.gb.gbshop.components.product;

import com.gb.gbshop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
