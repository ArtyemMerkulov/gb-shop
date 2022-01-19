package com.gb.gbshop.entity.manufacturer;

import com.gb.gbshop.entity.AbstractPersistableEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Manufacturer extends AbstractPersistableEntity<UUID> {

    private String title;

    private Integer version;

    private String createdBy;

    @CreationTimestamp
    private Timestamp createdDate;

    private String lastModifiedBy;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}