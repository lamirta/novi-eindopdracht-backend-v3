package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.services.WordListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WordListController {
    private final WordListService service;

    public WordListController(WordListService service) {
        this.service = service;
    }

    @GetMapping(value = "/wordlists")
    public ResponseEntity<Object> getAllWordLists() {
        List<WordListDto> allWordLists = service.getAllWordLists();
        return new ResponseEntity<>(allWordLists, HttpStatus.OK);
    }

    @GetMapping("/wordlists/{title}")
    public ResponseEntity<WordListDto> getWordListByTitle(@PathVariable("title") String title) {
        WordListDto wlDto = service.getWordListByTitle(title);

        return ResponseEntity.ok().body(wlDto);
    }

    @PostMapping(value = "/wordlists")
    public ResponseEntity<Object> createWordList(@Valid @RequestBody WordListDto wlDto) {
        service.createWordList(wlDto);
        return new ResponseEntity<>("Woordenlijst aangemaakt!", HttpStatus.CREATED);
    }





}
