package com.readowo.api.publishing.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bookStatus")
public class BookStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private String Name ;

    @OneToMany(mappedBy = "bookStatus", cascade = CascadeType.ALL)
    private List<Book> books ;



}
