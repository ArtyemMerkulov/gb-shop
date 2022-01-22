package com.gb.gbshop.components.category;

import com.gb.gbshop.entity.category.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Lazy
@Slf4j
public class CategoryFacadeImpl implements CategoryFacade {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Override
    @Async
    public Future<ResponseEntity<?>> findAll() {
        List<CategoryDto> categoryDtoList = categoryService.findAll()
                                                           .stream()
                                                           .map(category -> modelMapper.map(category, CategoryDto.class))
                                                           .collect(Collectors.toList());
        return new AsyncResult<>(categoryDtoList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(categoryDtoList, HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> findById(UUID id) {
        Category category = categoryService.findById(id)
                                           .orElse(null);
        return new AsyncResult<>(category == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> save(CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            if (categoryService.findById(categoryDto.getId())
                               .isPresent()) {
                return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
            }
        }
        saveEntity(categoryDto);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> update(UUID id, CategoryDto categoryDto) {
        if (id != null) {
            categoryDto.setId(id);
            saveEntity(categoryDto);
        }
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> delete(UUID id) {
        categoryService.delete(id);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    private void saveEntity(CategoryDto categoryDto) {
        categoryService.save(modelMapper.map(categoryDto, Category.class));
    }

}
