package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveLanguageDtos;
import com.readowo.api.publishing.Models.Language;
import com.readowo.api.publishing.Services.Communication.LanguageResponse;

import java.util.List;
import java.util.Optional;

public interface ILanguageService {

    List<Language> listLanguages();
    Optional<Language> findLanguageById(Long languageId);
    Language saveLanguage(SaveLanguageDtos saveLanguageDtos);
    LanguageResponse updateLanguage(Long id, SaveLanguageDtos saveLanguageDtos);
    LanguageResponse deleteLanguage(Long languageId);
}
