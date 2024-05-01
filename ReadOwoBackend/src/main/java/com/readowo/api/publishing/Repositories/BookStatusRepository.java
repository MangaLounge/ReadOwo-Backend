package com.readowo.api.publishing.Repositories;

import com.readowo.api.publishing.Models.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;




@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus,Long> {

}
