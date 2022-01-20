package com.gb.gbshop.components.manufacturer;

import com.gb.gbshop.entity.manufacturer.Manufacturer;
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
public class ManufacturerFacadeImpl implements ManufacturerFacade {

    private final ModelMapper modelMapper;
    private final ManufacturerService manufacturerService;

    @Override
    @Async
    public Future<ResponseEntity<?>> findAll() {
        List<ManufacturerDto> manufacturerDtoList = manufacturerService.findAll()
                                                           .stream()
                                                           .map(manufacturer -> modelMapper.map(manufacturer, ManufacturerDto.class))
                                                           .collect(Collectors.toList());
        return new AsyncResult<>(manufacturerDtoList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(manufacturerDtoList, HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> findById(UUID id) {
        Manufacturer manufacturer = manufacturerService.findById(id)
                                           .orElse(null);
        return new AsyncResult<>(manufacturer == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(modelMapper.map(manufacturer, ManufacturerDto.class), HttpStatus.OK));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> save(ManufacturerDto manufacturerDto) {
        if (manufacturerDto.getId() != null) {
            if (manufacturerService.findById(manufacturerDto.getId())
                               .isPresent()) {
                return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
            }
        }
        saveEntity(manufacturerDto);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> update(UUID id, ManufacturerDto manufacturerDto) {
        if (id != null) {
            manufacturerDto.setId(id);
            saveEntity(manufacturerDto);
        }
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    @Async
    public Future<ResponseEntity<?>> delete(UUID id) {
        manufacturerService.delete(id);
        return new AsyncResult<>(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    private void saveEntity(ManufacturerDto manufacturerDto) {
        manufacturerService.save(modelMapper.map(manufacturerDto, Manufacturer.class));
    }

}
