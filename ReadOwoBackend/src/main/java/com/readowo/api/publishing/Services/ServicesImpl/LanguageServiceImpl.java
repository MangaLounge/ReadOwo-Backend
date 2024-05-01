package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.ReadOwo.Repositories.IUnitOfWork;
import com.readowo.api.publishing.Dtos.GenreDtos;
import com.readowo.api.publishing.Dtos.LanguageDtos;
import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Dtos.SaveLanguageDtos;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Models.Language;
import com.readowo.api.publishing.Repositories.LanguageRepository;
import com.readowo.api.publishing.Services.Communication.GenreResponse;
import com.readowo.api.publishing.Services.Communication.LanguageResponse;
import com.readowo.api.publishing.Services.IServices.ILanguageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class LanguageServiceImpl implements ILanguageService {

    private final LanguageRepository languageRepository;
    private final IUnitOfWork unitOfWork;
    private final ModelMapper modelMapper;
    @Override
    public List<Language> listLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Optional<Language> findLanguageById(Long languageId) {
        return languageRepository.findById(languageId);
    }

    @Override
    public Language saveLanguage(SaveLanguageDtos saveLanguageDtos) {
        Language language = modelMapper.map(saveLanguageDtos, Language.class);
        return languageRepository.save(language);
    }


    @Override
    public LanguageResponse updateLanguage(Long id, SaveLanguageDtos saveLanguageDtos) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            Language existingLanguage = optionalLanguage.get();
            modelMapper.map(saveLanguageDtos, existingLanguage);
            Language updatedCha = languageRepository.save(existingLanguage);
            LanguageDtos languageDtos = modelMapper.map(updatedCha, LanguageDtos.class);
            return new LanguageResponse(String.valueOf(languageDtos));
        } else {
            return new LanguageResponse("Book not found");
        }
    }

    @Override
    public LanguageResponse deleteLanguage(Long languageId) {
        Optional<Language> existingLanguage = languageRepository.findById(languageId);
        if (existingLanguage.isPresent()) {
            try {
                languageRepository.delete(existingLanguage.get());
                unitOfWork.complete();
                return new LanguageResponse(existingLanguage.get());
            } catch (Exception e) {
                return new LanguageResponse("An error occurred while deleting the language: " + e.getMessage());
            }
        } else {
            return new LanguageResponse("Language not found.");
        }
    }
}
