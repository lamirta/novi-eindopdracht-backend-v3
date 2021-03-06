package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.services.WordListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class WordListController {
    private final WordListService service;

    public WordListController(WordListService service) {
        this.service = service;
    }

    @GetMapping("/wordlists")
    public ResponseEntity<Object> getAllWordLists() {
        List<WordListDto> allWordLists = service.getAllWordLists();
        return new ResponseEntity<>(allWordLists, HttpStatus.OK);
    }

    @GetMapping("/wordlists/{title}")
    public ResponseEntity<WordListDto> getWordListByTitle(@PathVariable("title") String title) {
        WordListDto wlDto = service.getWordListByTitle(title);
        return ResponseEntity.ok().body(wlDto);
    }

    @PostMapping("/wordlists")
    public ResponseEntity<Object> createWordList(@Valid @RequestBody WordListDto wlDto) {
        service.createWordList(wlDto);
        return new ResponseEntity<>("Woordenlijst aangemaakt!", HttpStatus.CREATED);
    }

    @DeleteMapping("/wordlists/{title}")
    public void deleteWordList(@PathVariable("title") String title) {
        service.deleteWordList(title);
    }

    @PutMapping("/wordlists/{title}")
    public ResponseEntity<WordListDto> updateWordList(@PathVariable("title") String title, @RequestBody WordListDto wordListDto) {
        WordListDto wlDto = service.updateWordList(title, wordListDto);
        return ResponseEntity.ok().body(wlDto);
    }

}
