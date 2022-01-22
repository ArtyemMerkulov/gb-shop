package com.gb.gbshop.components.manufacturer;

import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.concurrent.Future;

public interface ManufacturerFacade {

    Future<ResponseEntity<?>> findAll();

    Future<ResponseEntity<?>> findById(UUID id);

    Future<ResponseEntity<?>> save(ManufacturerDto ManufacturerDto);

    Future<ResponseEntity<?>> update(UUID id, ManufacturerDto ManufacturerDto);

    Future<ResponseEntity<?>> delete(UUID id);

}
