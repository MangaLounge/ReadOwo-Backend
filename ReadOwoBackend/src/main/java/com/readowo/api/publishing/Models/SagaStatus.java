package com.readowo.api.publishing.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SagaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private String Name;

    @OneToMany(mappedBy = "sagaStatus", cascade = CascadeType.ALL)
    private List<Saga> sagas ;


}
