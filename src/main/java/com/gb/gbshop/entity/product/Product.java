package com.gb.gbshop.entity.product;

import com.gb.gbshop.entity.AbstractPersistableEntity;
import com.gb.gbshop.entity.category.Category;
import com.gb.gbshop.entity.manufacturer.Manufacturer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product extends AbstractPersistableEntity<UUID> {

    private String title;

    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private LocalDate manufactureDate;

    private Integer version;

    private String createdBy;

    @CreationTimestamp
    private Timestamp createdDate;

    private String lastModifiedBy;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

}
