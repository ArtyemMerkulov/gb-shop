package com.gb.gbshop.rest.version.v1.manufacturer;

import com.gb.gbshop.components.manufacturer.ManufacturerDto;
import com.gb.gbshop.components.manufacturer.ManufacturerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Lazy
@RestController
@RequestMapping("/api/v1/manufacturer")
@Slf4j
public class ManufacturerController {

    private final ManufacturerFacade manufacturerFacade;

    public ManufacturerController(ManufacturerFacade manufacturerFacade) {
        this.manufacturerFacade = manufacturerFacade;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ExecutionException, InterruptedException {
        return manufacturerFacade.findAll()
                                 .get();
    }

    @GetMapping("/{manufacturerId}")
    public ResponseEntity<?> findById(@PathVariable("manufacturerId") UUID id) throws ExecutionException, InterruptedException {
        return manufacturerFacade.findById(id)
                                 .get();
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody ManufacturerDto manufacturerDto) throws ExecutionException, InterruptedException {
        return manufacturerFacade.save(manufacturerDto)
                                 .get();
    }

    @PutMapping("/{manufacturerId}")
    public ResponseEntity<?> update(@PathVariable("manufacturerId") UUID id,
                                    @Validated @RequestBody ManufacturerDto manufacturerDto) throws ExecutionException, InterruptedException {
        return manufacturerFacade.update(id, manufacturerDto)
                                 .get();
    }

    @DeleteMapping("/{manufacturerId}")
    public ResponseEntity<?> delete(@PathVariable("manufacturerId") UUID id) throws ExecutionException, InterruptedException {
        return manufacturerFacade.delete(id)
                                 .get();
    }

}
