package com.readowo.api.publishing.controllers;

import com.readowo.api.publishing.Dtos.SagaDtos;
import com.readowo.api.publishing.Dtos.SaveSagaDtos;
import com.readowo.api.publishing.Models.Saga;
import com.readowo.api.publishing.Services.Communication.SagaResponse;
import com.readowo.api.publishing.Services.ServicesImpl.SagaServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/saga")
@AllArgsConstructor
public class SagaController {
    private final SagaServiceImpl sagaService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<Saga>> getAllSagas() {
        List<Saga> sagas = sagaService.listSagas();
        return ResponseEntity.ok().body(sagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saga> getOneSaga(@PathVariable(value = "id") Long sagaId) {
        Optional<Saga> saga = sagaService.findSagaById(sagaId);
        return saga.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SagaDtos> saveSaga(@RequestBody SaveSagaDtos saveSagaDtos) {
        Saga saga = modelMapper.map(saveSagaDtos, Saga.class);
        Saga createdSaga = sagaService.saveSaga(saveSagaDtos);
        SagaDtos sagaDtos = modelMapper.map(createdSaga, SagaDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(sagaDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SagaResponse> updateSaga(@PathVariable("id") Long id, @RequestBody SaveSagaDtos saveSagaDtos) {
        SagaResponse response = sagaService.updateSaga(id, saveSagaDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SagaResponse> deleteSaga(@PathVariable(value = "id") Long sagaId) {
        SagaResponse response = sagaService.deleteSaga(sagaId);
        return ResponseEntity.ok().body(response);
    }

}
