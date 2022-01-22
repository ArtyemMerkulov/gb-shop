package com.gb.gbshop.components.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class CategoryDto {

    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private Integer version;

    @NotNull
    private String createdBy;

    private Timestamp createdDate;

    @NotNull
    private String lastModifiedBy;

    private Timestamp lastModifiedDate;

}
