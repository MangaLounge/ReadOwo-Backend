package com.readowo.api.publishing.controllers;

import com.readowo.api.publishing.Dtos.SagaStatusDtos;
import com.readowo.api.publishing.Dtos.SaveSagaStatusDtos;
import com.readowo.api.publishing.Models.SagaStatus;
import com.readowo.api.publishing.Services.Communication.SagaStatusResponse;
import com.readowo.api.publishing.Services.ServicesImpl.SagaStatusServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/sagaStatus")
@AllArgsConstructor
public class SagaStatusController {
    private final SagaStatusServiceImpl sagaStatusService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<SagaStatus>> getAllSagaStatuses() {
        List<SagaStatus> sagaStatuses = sagaStatusService.getAllSagaStatuses();
        return ResponseEntity.ok(sagaStatuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SagaStatus> getSagaStatusById(@PathVariable("id") Long sagaStatusId) {
        Optional<SagaStatus> sagaStatus = sagaStatusService.getSagaStatusById(sagaStatusId);
        return sagaStatus.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SagaStatusDtos> saveSagaStatus(@RequestBody SaveSagaStatusDtos saveSagaStatusDtos) {
        SagaStatus sagaStatus = modelMapper.map(saveSagaStatusDtos, SagaStatus.class);
        SagaStatus createdSagaStatus = sagaStatusService.saveSagaStatus(saveSagaStatusDtos);
        SagaStatusDtos sagaStatusDtos = modelMapper.map(createdSagaStatus, SagaStatusDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(sagaStatusDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SagaStatusResponse> updateSagaStatus(@PathVariable("id") Long id, @RequestBody SaveSagaStatusDtos saveSagaStatusDtos) {
        SagaStatusResponse response = sagaStatusService.updateSagaStatus(id, saveSagaStatusDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SagaStatusResponse> deleteSagaStatus(@PathVariable("id") Long sagaStatusId) {
        SagaStatusResponse response = sagaStatusService.deleteSagaStatus(sagaStatusId);
        return ResponseEntity.ok(response);
    }

}
