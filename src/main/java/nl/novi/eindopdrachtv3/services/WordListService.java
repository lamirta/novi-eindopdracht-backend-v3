package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.WordList;

import java.util.List;

public interface WordListService {
    List<WordListDto> getAllWordLists();
    WordListDto getWordListByTitle(String title);
    WordListDto createWordList(WordListDto wordListDto);
    void deleteWordList(String title);
    WordListDto updateWordList(String title, WordListDto wordListDto);
    WordList getWordList(String title);
//    void deleteWordsInWordList(List<String> words);
}
