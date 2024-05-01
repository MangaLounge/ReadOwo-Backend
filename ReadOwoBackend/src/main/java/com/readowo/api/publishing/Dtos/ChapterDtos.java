package com.readowo.api.publishing.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDtos {

    private Long Id ;

    private String Title;
    private String Document_content_url;

    private BookDtos book;
}
