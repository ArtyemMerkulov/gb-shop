package com.gb.gbshop.components.product;

import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.concurrent.Future;

public interface ProductFacade {

    Future<ResponseEntity<?>> findAll();

    Future<ResponseEntity<?>> findById(UUID id);

    Future<ResponseEntity<?>> save(ProductDto productDto);

    Future<ResponseEntity<?>> update(UUID id, ProductDto productDto);

    Future<ResponseEntity<?>> delete(UUID id);

}
