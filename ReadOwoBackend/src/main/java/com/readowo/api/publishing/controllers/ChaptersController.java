package com.readowo.api.publishing.controllers;



import com.readowo.api.publishing.Dtos.ChapterDtos;
import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Services.Communication.ChapterResponse;
import com.readowo.api.publishing.Services.ServicesImpl.ChaptersServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/chapters")
public class ChaptersController {


    private final ChaptersServiceImpl chaptersService;
    private final ModelMapper modelMapper;

    @Autowired
    public ChaptersController(ChaptersServiceImpl chaptersService, ModelMapper modelMapper) {
        this.chaptersService = chaptersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Chapters>> getAllChapters() {
        List<Chapters> chaptersList = chaptersService.getAllChapters();
        return ResponseEntity.ok().body(chaptersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChaptersById(@PathVariable Long id) {
        Optional<Chapters> chapters = chaptersService.getChaptersById(id);
        return chapters.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PostMapping
    public ResponseEntity<ChapterDtos> saveChapters(@RequestBody SaveChapterDtos saveChapterDtos) {
        Chapters chapters = modelMapper.map(saveChapterDtos, Chapters.class);
        Chapters createdChapters = chaptersService.saveChapters(saveChapterDtos);
        ChapterDtos chapterDtos = modelMapper.map(createdChapters, ChapterDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(chapterDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterResponse> updateChapters(@PathVariable("id") Long id, @RequestBody SaveChapterDtos saveChapterDtos) {
        ChapterResponse response = chaptersService.updateChapters(id, saveChapterDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChapterResponse> deleteChapters(@PathVariable("id") Long chapterId) {
        ChapterResponse response = chaptersService.deleteChapters(chapterId);
        return ResponseEntity.ok().body(response);
    }
}
