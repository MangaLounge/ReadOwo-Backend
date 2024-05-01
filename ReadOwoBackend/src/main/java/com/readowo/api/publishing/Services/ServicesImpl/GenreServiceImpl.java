package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.ReadOwo.Repositories.IUnitOfWork;
import com.readowo.api.publishing.Dtos.ChapterDtos;
import com.readowo.api.publishing.Dtos.GenreDtos;
import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Repositories.GenreRepository;
import com.readowo.api.publishing.Services.Communication.ChapterResponse;
import com.readowo.api.publishing.Services.Communication.GenreResponse;
import com.readowo.api.publishing.Services.IServices.IGenreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class GenreServiceImpl implements IGenreService {
    private final GenreRepository genreRepository;
    private final IUnitOfWork unitOfWork;
    private final ModelMapper modelMapper;

    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public Genre saveGenre(SaveGenreDtos saveGenreDtos) {
        Genre genre = modelMapper.map(saveGenreDtos, Genre.class);
        return genreRepository.save(genre);
    }


    @Override
    public GenreResponse updateGenre(Long id, SaveGenreDtos saveGenreDtos) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre existingGenre = optionalGenre.get();
            modelMapper.map(saveGenreDtos, existingGenre);
            Genre updatedCha = genreRepository.save(existingGenre);
            GenreDtos genreDtos = modelMapper.map(updatedCha, GenreDtos.class);
            return new GenreResponse(String.valueOf(genreDtos));
        } else {
            return new GenreResponse("Book not found");
        }
    }
    @Override
    public GenreResponse deleteGenre(Long genreId) {
        Optional<Genre> existingGenreOptional = genreRepository.findById(genreId);
        if (existingGenreOptional.isPresent()) {
            Genre existingGenre = existingGenreOptional.get();
            try {
                genreRepository.delete(existingGenre);
                unitOfWork.complete();
                return new GenreResponse(existingGenre);
            } catch (Exception e) {
                return new GenreResponse("An error occurred while deleting the genre: " + e.getMessage());
            }
        } else {
            return new GenreResponse("Genre not found.");
        }
    }


}
