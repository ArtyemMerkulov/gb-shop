package com.gb.gbshop.components.manufacturer;

import com.gb.gbshop.entity.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
