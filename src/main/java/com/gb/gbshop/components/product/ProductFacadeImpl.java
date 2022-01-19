package com.gb.gbshop.components.product;

import com.gb.gbshop.entity.product.Product;
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

@Service
@Lazy
@Slf4j
public class ProductFacadeImpl implements ProductFacade {

    private final ModelMapper modelMapper;
    private final ProductService productService;

    public ProductFacadeImpl(ModelMapper modelMapper,
                             ProductService productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> findAll() {
        List<ProductDto> productDtoList = productService.findAll()
                                                        .stream()
                                                        .map(product -> modelMapper.map(product, ProductDto.class))
                                                        .collect(Collectors.toList());
        return new AsyncResult<>(productDtoList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(productDtoList, HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> findById(UUID id) {
        Product product = productService.findById(id)
                                        .orElse(null);
        return new AsyncResult<>(product == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(modelMapper.map(product, ProductDto.class), HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> save(ProductDto productDto) {
        if (productDto.getId() != null) {
            if (productService.findById(productDto.getId())
                              .isPresent()) {
                return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
            }
        }
        saveEntity(productDto);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> update(UUID id, ProductDto productDto) {
        if (id != null) {
            productDto.setId(id);
            saveEntity(productDto);
        }
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> delete(UUID id) {
        productService.delete(id);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    private void saveEntity(ProductDto productDto) {
        productService.save(modelMapper.map(productDto, Product.class));
    }

}
