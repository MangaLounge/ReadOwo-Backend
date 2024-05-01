package com.readowo.api.publishing.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "saga")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private String Title;
    private String Synopsis;
    @ManyToOne
    @JoinColumn(name = "sagaStatus_id", nullable = false)
    @JsonIgnore
    private SagaStatus sagaStatus;


    @OneToMany(mappedBy = "saga", cascade = CascadeType.ALL)
    private List<Book> books ;


}
