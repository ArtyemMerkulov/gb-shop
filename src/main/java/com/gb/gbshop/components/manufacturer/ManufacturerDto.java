package com.gb.gbshop.components.manufacturer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ManufacturerDto {

    private UUID id;

    @NotNull
    private String name;

}
