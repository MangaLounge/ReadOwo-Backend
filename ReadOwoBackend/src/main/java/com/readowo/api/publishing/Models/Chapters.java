package com.readowo.api.publishing.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private String Title;
    private String Document_content_url;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
