package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.WordListDto;

import java.util.List;

public interface WordListService {
    List<WordListDto> getAllWordLists();
    WordListDto getWordListByTitle(String title);
    WordListDto createWordList(WordListDto wordListDto);
    void deleteWordList(String title);
    WordListDto updateWordList(String title, WordListDto wordListDto);
//    void deleteWordsInWordList(List<String> words);  // dat doe je al met update / Put volgens mij..
}
