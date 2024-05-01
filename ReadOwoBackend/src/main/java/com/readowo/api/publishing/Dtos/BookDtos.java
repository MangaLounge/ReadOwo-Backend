package com.readowo.api.publishing.Dtos;


import com.readowo.api.publishing.Models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtos {


    private Long Id ;
    private String Title ;
    private String Synopsis ;
    private String PublishedAt ;
    private String ThumbnailUrl ;
    private Long ProfileId;

    private Language language;


    private Saga saga;

    private BookStatus bookStatus;



}
