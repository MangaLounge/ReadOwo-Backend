package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveSagaDtos;
import com.readowo.api.publishing.Models.Saga;
import com.readowo.api.publishing.Services.Communication.SagaResponse;

import java.util.List;
import java.util.Optional;

public interface ISagaService {
    public List<Saga> listSagas();
    Optional<Saga> findSagaById(Long sagaId);
    Saga saveSaga(SaveSagaDtos saveSagaDtos);
    SagaResponse updateSaga(Long id, SaveSagaDtos saveSagaDtos) ;
    SagaResponse deleteSaga(Long sagaId);
}
