package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Services.Communication.ChapterResponse;

import java.util.List;
import java.util.Optional;

public interface IChaptersService {

    List<Chapters> getAllChapters();
    Optional<Chapters> getChaptersById(Long chaptersId);
    Chapters saveChapters(SaveChapterDtos saveChapterDtos);
    ChapterResponse deleteChapters(Long chaptersId);
    ChapterResponse updateChapters(Long id, SaveChapterDtos saveChapterDtos);

}
