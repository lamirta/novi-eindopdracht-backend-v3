package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.services.WordListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// @Valid moet dat niet steeds gebruikt worden hier?

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

    @DeleteMapping("/wordlists/{title}") //delete ook woorden uit woordenlijst functie? "/wordlists/{title}/{words}"
    public void deleteWordList(@PathVariable("title") String title) {

        service.deleteWordList(title);
    }

    @PutMapping("/wordlists/{title}")
    public WordListDto updateWordList(@PathVariable("title") String title, @RequestBody WordListDto wordListDto) {

        WordListDto wlDto = service.updateWordList(title, wordListDto);

        return wlDto;
//        return new ResponseEntity<>("Woordenlijst aangepast", HttpStatus.OK); kan ik een message terug geven? Hoe?
    }


// // note: volgens mij moet je delete word in wordlist, een @Put doen met die woorden die meoten blijven..?
    // dus met de put die ik hierboven al heb, kan je ook gewoon woorden aanpassen. je past de hele lijst gewoon aan..


//    @DeleteMapping("/wordlists/title/{words}") //delete ook woorden uit woordenlijst functie?
//    public void deleteWordsInWordList(@PathVariable("words") List<String> words) {
//
//        service.deleteWordsInWordList(words);
//    }

//    // Kan deze PutMapping wel?? Als ja, dan method in service zetten
//    @PutMapping("/wordlists/{title}/{words}")
//    public WordListDto updateWordsInWordList(@PathVariable("title") String title, @PathVariable List<String> words, @RequestBody WordListDto wordListDto) {
//
//        WordListDto wlDto = service.updateWordsInWordList(title, words, wordListDto);
//
//        return wlDto;
//    }

}
