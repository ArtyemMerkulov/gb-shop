package com.gb.gbshop.rest.version.v1.product;

import com.gb.gbshop.components.product.ProductDto;
import com.gb.gbshop.components.product.ProductFacade;
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
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ExecutionException, InterruptedException {
        return productFacade.findAll()
                            .get();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> findById(@PathVariable("productId") UUID id) throws ExecutionException, InterruptedException {
        return productFacade.findById(id)
                            .get();
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody ProductDto productDto) throws ExecutionException, InterruptedException {
        return productFacade.save(productDto)
                            .get();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable("productId") UUID id,
                                    @Validated @RequestBody ProductDto productDto) throws ExecutionException, InterruptedException {
        return productFacade.update(id, productDto)
                            .get();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable("productId") UUID id) throws ExecutionException, InterruptedException {
        return productFacade.delete(id)
                            .get();
    }

}
