package com.readowo.api.publishing.controllers;

import com.readowo.api.publishing.Dtos.GenreDtos;
import com.readowo.api.publishing.Dtos.LanguageDtos;
import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Dtos.SaveLanguageDtos;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Models.Language;
import com.readowo.api.publishing.Services.Communication.GenreResponse;
import com.readowo.api.publishing.Services.Communication.LanguageResponse;
import com.readowo.api.publishing.Services.ServicesImpl.LanguageServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/languages")
@AllArgsConstructor
public class LanguageController {
    private final LanguageServiceImpl languageService;
    private final ModelMapper modelMapper;



    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> languages = languageService.listLanguages();
        return ResponseEntity.ok().body(languages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable("id") Long languageId) {
        Optional<Language> language = languageService.findLanguageById(languageId);
        return language.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LanguageDtos> saveLanguage(@RequestBody SaveLanguageDtos saveLanguageDtos) {
        Language language = modelMapper.map(saveLanguageDtos, Language.class);
        Language createdLanguage = languageService.saveLanguage(saveLanguageDtos);
        LanguageDtos languageDtos = modelMapper.map(createdLanguage, LanguageDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(languageDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageResponse> updateLanguage(@PathVariable("id") Long id, @RequestBody SaveLanguageDtos saveLanguageDtos) {
        LanguageResponse response = languageService.updateLanguage(id, saveLanguageDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LanguageResponse> deleteLanguage(@PathVariable("id") Long languageId) {
        LanguageResponse response = languageService.deleteLanguage(languageId);
        return ResponseEntity.ok().body(response);
    }
}
