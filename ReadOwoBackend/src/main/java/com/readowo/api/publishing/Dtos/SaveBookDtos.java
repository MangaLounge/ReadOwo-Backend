package com.readowo.api.publishing.Dtos;


import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Models.Language;
import com.readowo.api.publishing.Models.Saga;
import lombok.Data;


@Data

public class SaveBookDtos {


    private String Title ;
    private String Synopsis ;
    private String PublishedAt ;
    private String ThumbnailUrl ;
    private Long ProfileId;

    private Long languageId;


    private Long sagaId;

    private Long bookStatusId;
}
