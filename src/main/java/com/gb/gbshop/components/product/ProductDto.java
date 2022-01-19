package com.gb.gbshop.components.product;

import com.gb.gbshop.components.manufacturer.ManufacturerDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private BigDecimal cost;

    @NotNull
    private LocalDate manufactureDate;

    @NotNull
    private Integer version;

    @NotNull
    private String createdBy;

    private Timestamp createdDate;

    @NotNull
    private String lastModifiedBy;

    private Timestamp lastModifiedDate;

    @NotNull
    private String status;

    @NotNull
    private ManufacturerDto manufacturer;

}
