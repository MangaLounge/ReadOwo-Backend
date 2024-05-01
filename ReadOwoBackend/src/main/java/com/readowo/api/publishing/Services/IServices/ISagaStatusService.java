package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveSagaStatusDtos;
import com.readowo.api.publishing.Models.SagaStatus;
import com.readowo.api.publishing.Services.Communication.SagaStatusResponse;

import java.util.List;
import java.util.Optional;

public interface ISagaStatusService {

    List<SagaStatus> getAllSagaStatuses();
    Optional<SagaStatus> getSagaStatusById(Long sagaStatusId);
    SagaStatus saveSagaStatus(SaveSagaStatusDtos saveSagaStatusDtos) ;

    SagaStatusResponse deleteSagaStatus(Long sagaStatusId);
    SagaStatusResponse updateSagaStatus(Long id, SaveSagaStatusDtos saveSagaStatusDtos);
}
