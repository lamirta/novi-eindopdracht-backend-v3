package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordListServiceImpl implements WordListService {

    private final WordListRepository wordListRepository;

    public WordListServiceImpl(WordListRepository wordListRepository) {
        this.wordListRepository = wordListRepository;
    }


    @Override
    public List<WordListDto> getAllWordLists() {
        return null;
    }

    @Override
    public WordListDto getWordListByTitle(String title) {
        return null;
    }

    @Override
    public WordListDto createWordList(WordListDto wordListDto) {
        return null;
    }

    @Override
    public void deleteWordList(String title) {

    }

    @Override
    public WordListDto updateWordList(String title, WordListDto wordListDto) {
        return null;
    }
}
