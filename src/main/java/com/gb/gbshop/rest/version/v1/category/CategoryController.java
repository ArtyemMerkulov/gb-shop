package com.gb.gbshop.rest.version.v1.category;

import com.gb.gbshop.components.category.CategoryDto;
import com.gb.gbshop.components.category.CategoryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@ControllerAdvice
@Lazy
@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ExecutionException, InterruptedException {
        return categoryFacade.findAll()
                             .get();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findById(@PathVariable("categoryId") UUID id) throws ExecutionException, InterruptedException {
        return categoryFacade.findById(id)
                             .get();
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody CategoryDto categoryDto) throws ExecutionException, InterruptedException {
        return categoryFacade.save(categoryDto)
                             .get();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(@PathVariable("categoryId") UUID id,
                                    @Validated @RequestBody CategoryDto categoryDto) throws ExecutionException, InterruptedException {
        return categoryFacade.update(id, categoryDto)
                             .get();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable("categoryId") UUID id) throws ExecutionException, InterruptedException {
        return categoryFacade.delete(id)
                             .get();
    }

}
