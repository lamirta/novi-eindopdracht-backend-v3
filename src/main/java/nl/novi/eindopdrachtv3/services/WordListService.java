package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.models.WordList;

import java.util.List;

//entities omzetten naar Dto's in attributen
public interface WordListService {
    List<WordList> getAllWordLists();
    WordList getWordListByTitle(String title);
    WordList createWordList(WordList wordList);
    void deleteWordList(String title);
    WordList updateWordList();
}
