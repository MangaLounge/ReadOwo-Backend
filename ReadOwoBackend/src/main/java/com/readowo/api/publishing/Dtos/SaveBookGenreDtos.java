package com.readowo.api.publishing.Dtos;

import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Models.Language;
import com.readowo.api.publishing.Models.Saga;
import lombok.Data;

@Data
public class SaveBookGenreDtos {


   private Long bookId;
   private Long genreId;
}
