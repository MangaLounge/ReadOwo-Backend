package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.Shared.Persistence.Repositories.UnitOfWork;
import com.readowo.api.publishing.Dtos.SagaStatusDtos;
import com.readowo.api.publishing.Dtos.SaveSagaStatusDtos;
import com.readowo.api.publishing.Models.SagaStatus;
import com.readowo.api.publishing.Repositories.SagaStatusRepository;
import com.readowo.api.publishing.Services.Communication.SagaStatusResponse;
import com.readowo.api.publishing.Services.IServices.ISagaStatusService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class SagaStatusServiceImpl  implements ISagaStatusService {
    private final SagaStatusRepository sagaStatusRepository;
    private final UnitOfWork unitOfWork;
    private final ModelMapper modelMapper;
    @Override
    public List<SagaStatus> getAllSagaStatuses() {
        return sagaStatusRepository.findAll();
    }

    @Override
    public Optional<SagaStatus> getSagaStatusById(Long sagaStatusId) {
        return sagaStatusRepository.findById(sagaStatusId);
    }

    @Override
    public SagaStatus saveSagaStatus(SaveSagaStatusDtos saveSagaStatusDtos) {
        SagaStatus sagaStatus = modelMapper.map(saveSagaStatusDtos, SagaStatus.class);
        return sagaStatusRepository.save(sagaStatus);
    }

    @Override
    public SagaStatusResponse updateSagaStatus(Long id, SaveSagaStatusDtos saveSagaStatusDtos) {
        Optional<SagaStatus> optionalSagaStatus = sagaStatusRepository.findById(id);
        if (optionalSagaStatus.isPresent()) {
            SagaStatus existingSagaStatus = optionalSagaStatus.get();
            modelMapper.map(saveSagaStatusDtos, existingSagaStatus);
            SagaStatus updatedSagaStatus = sagaStatusRepository.save(existingSagaStatus);
            SagaStatusDtos sagaStatusDtos = modelMapper.map(updatedSagaStatus, SagaStatusDtos.class);
            return new SagaStatusResponse(String.valueOf(sagaStatusDtos));
        } else {
            return new SagaStatusResponse("SagaStatus not found");
        }
    }

    @Override
    public SagaStatusResponse deleteSagaStatus(Long sagaStatusId) {
        Optional<SagaStatus> existingSagaStatus = sagaStatusRepository.findById(sagaStatusId);
        if (!existingSagaStatus.isPresent()) {
            return new SagaStatusResponse("Saga status not found.");
        }

        try {
            sagaStatusRepository.delete(existingSagaStatus.get());
            unitOfWork.complete();
            return new SagaStatusResponse(existingSagaStatus.get());
        } catch (Exception e) {
            return new SagaStatusResponse("An error occurred while deleting the saga status: " + e.getMessage());
        }
    }
}
