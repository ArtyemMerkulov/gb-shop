package com.gb.gbshop.components.manufacturer;

import com.gb.gbshop.entity.manufacturer.Manufacturer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(UUID id);

    Manufacturer save(Manufacturer Manufacturer);

    void delete(UUID id);

}
