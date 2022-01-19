package com.gb.gbshop.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public abstract class AbstractPersistableEntity<PK extends Serializable> extends AbstractPersistable<PK> {

    public void setId(PK id) {
        super.setId(id);
    }

}
