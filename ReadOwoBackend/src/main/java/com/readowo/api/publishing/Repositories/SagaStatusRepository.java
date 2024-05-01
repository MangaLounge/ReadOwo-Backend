package com.readowo.api.publishing.Repositories;

import com.readowo.api.publishing.Models.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaStatusRepository extends JpaRepository<SagaStatus, Long> {
}
