package com.gb.gbshop.components.category;


import com.gb.gbshop.entity.category.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(UUID id);

    Category save(Category category);

    void delete(UUID id);

}
