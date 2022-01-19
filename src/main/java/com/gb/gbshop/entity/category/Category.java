package com.gb.gbshop.entity.category;

import com.gb.gbshop.entity.AbstractPersistableEntity;
import com.gb.gbshop.entity.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Category extends AbstractPersistableEntity<UUID> {

    private String title;

    private Integer version;

    private String createdBy;

    @CreationTimestamp
    private Timestamp createdDate;

    private String lastModifiedBy;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

}
