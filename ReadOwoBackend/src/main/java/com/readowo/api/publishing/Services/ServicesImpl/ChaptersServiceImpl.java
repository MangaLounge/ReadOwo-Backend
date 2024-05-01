package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.Shared.Persistence.Repositories.UnitOfWork;
import com.readowo.api.publishing.Dtos.ChapterDtos;
import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Repositories.BookRepository;
import com.readowo.api.publishing.Repositories.ChaptersRepository;
import com.readowo.api.publishing.Services.Communication.ChapterResponse;
import com.readowo.api.publishing.Services.IServices.IChaptersService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Data
@AllArgsConstructor
public class ChaptersServiceImpl implements IChaptersService {

    private final ChaptersRepository chaptersRepository;
    private final UnitOfWork unitOfWork;

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<Chapters> getAllChapters() {
        return chaptersRepository.findAll();
    }

    @Override
    public Optional<Chapters> getChaptersById(Long chaptersId) {
        return chaptersRepository.findById(chaptersId);
    }

    @Override
    public Chapters saveChapters(SaveChapterDtos saveChapterDtos) {
        Chapters chapters = modelMapper.map(saveChapterDtos, Chapters.class);
        return chaptersRepository.save(chapters);
    }

    @Override
    public ChapterResponse deleteChapters(Long chaptersId) {

        Optional<Chapters> existingChapters = chaptersRepository.findById(chaptersId);
        if (!existingChapters.isPresent()) {
            return new ChapterResponse("Book Genre not found.");
        }

        try {
            chaptersRepository.delete(existingChapters.get());
            unitOfWork.complete();
            return new ChapterResponse(existingChapters.get());
        } catch (Exception e) {
            return new ChapterResponse("An error occurred while deleting the book: " + e.getMessage());
        }
    }




    @Override
    public ChapterResponse updateChapters(Long id, SaveChapterDtos saveChapterDtos) {
        Optional<Chapters> optionalChapters = chaptersRepository.findById(id);
        if (optionalChapters.isPresent()) {
            Chapters existingChapters = optionalChapters.get();
            modelMapper.map(saveChapterDtos, existingChapters);
            Chapters updatedCha = chaptersRepository.save(existingChapters);
            ChapterDtos chapterDtos = modelMapper.map(updatedCha, ChapterDtos.class);
            return new ChapterResponse(String.valueOf(chapterDtos));
        } else {
            return new ChapterResponse("Book not found");
        }
    }
}
