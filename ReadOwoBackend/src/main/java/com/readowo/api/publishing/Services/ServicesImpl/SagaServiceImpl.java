package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.ReadOwo.Repositories.IUnitOfWork;
import com.readowo.api.publishing.Dtos.SagaDtos;
import com.readowo.api.publishing.Dtos.SaveSagaDtos;
import com.readowo.api.publishing.Models.Saga;
import com.readowo.api.publishing.Repositories.SagaRepository;
import com.readowo.api.publishing.Services.Communication.SagaResponse;
import com.readowo.api.publishing.Services.IServices.ISagaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class SagaServiceImpl implements ISagaService {
    private final SagaRepository sagaRepository;
    private final IUnitOfWork unitOfWork;
    private final SagaStatusRepository sagaStatusRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Saga> listSagas() {
        return sagaRepository.findAll();
    }

    @Override
    public Optional<Saga> findSagaById(Long sagaId) {
        return sagaRepository.findById(sagaId);
    }

    @Override
    public Saga saveSaga(SaveSagaDtos saveSagaDtos) {
        Saga saga = modelMapper.map(saveSagaDtos, Saga.class);
        return sagaRepository.save(saga);
    }

    @Override
    public SagaResponse updateSaga(Long id, SaveSagaDtos saveSagaDtos) {
        Optional<Saga> optionalSaga = sagaRepository.findById(id);
        if (optionalSaga.isPresent()) {
            Saga existingSaga = optionalSaga.get();
            modelMapper.map(saveSagaDtos, existingSaga);
            Saga updatedSaga = sagaRepository.save(existingSaga);
            SagaDtos sagaDtos = modelMapper.map(updatedSaga, SagaDtos.class);
            return new SagaResponse(String.valueOf(sagaDtos));
        } else {
            return new SagaResponse("Saga not found");
        }
    }

    @Override
    public SagaResponse deleteSaga(Long sagaId) {
        Optional<Saga> existingSaga = sagaRepository.findById(sagaId);
        if (!existingSaga.isPresent()) {
            return new SagaResponse("Saga not found.");
        }
        try {
            sagaRepository.delete(existingSaga.get());
            unitOfWork.complete();
            return new SagaResponse(existingSaga.get());
        } catch (Exception e) {
            return new SagaResponse("An error occurred while deleting the saga: " + e.getMessage());
        }
    }

}
