package com.gb.gbshop.components.category;

import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.concurrent.Future;

public interface CategoryFacade {

    Future<ResponseEntity<?>> findAll();

    Future<ResponseEntity<?>> findById(UUID id);

    Future<ResponseEntity<?>> save(CategoryDto categoryDto);

    Future<ResponseEntity<?>> update(UUID id, CategoryDto categoryDto);

    Future<ResponseEntity<?>> delete(UUID id);

}
