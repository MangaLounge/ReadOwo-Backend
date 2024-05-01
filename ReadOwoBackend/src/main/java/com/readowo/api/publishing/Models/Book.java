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
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;
    private String Title ;
    private String Synopsis ;
    private String PublishedAt ;
    private String ThumbnailUrl ;
    private Long ProfileId;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    @JsonIgnore
    private Language language;

    @ManyToOne
    @JoinColumn(name = "saga_id", nullable = false)
    @JsonIgnore
    private Saga saga;

    @ManyToOne
    @JoinColumn(name = "bookStatus_id", nullable = false)
    @JsonIgnore
    private BookStatus bookStatus;



    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Chapters> chapters ;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookGenre> bookGenres ;



}
