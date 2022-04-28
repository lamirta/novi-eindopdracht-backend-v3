package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.exceptions.TitleNotFoundException;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordListServiceImpl implements WordListService {

    @Autowired
    private WordListRepository wordListRepository;


    // Moet hier geen exception gegooit worden? If geen wordlists, dan, "there are no wordlists yet"
    @Override
    public List<WordListDto> getAllWordLists() {
        List<WordList> wlList = wordListRepository.findAll();
        List<WordListDto> wlDtoList = new ArrayList<>();

        for(WordList wl : wlList) {
            WordListDto wldto = new WordListDto(wl.getTitle(), wl.getWords());
            wlDtoList.add(wldto);
        }
        return wlDtoList;
    }

    @Override
    public WordListDto getWordListByTitle(String title) {
        WordListDto wldto = new WordListDto();
        if (wordListRepository.findById(title).isPresent()) {
            WordList wl = wordListRepository.findById(title).get();
            wldto.setTitle(wl.getTitle());
            wldto.setWords(wl.getWords());
            return wldto;
        } else {
            throw new TitleNotFoundException(title);
        }
    }

    @Override
    public WordListDto createWordList(WordListDto wordListDto) {
        WordList wl = new WordList();

        wl.setTitle(wordListDto.getTitle());
        wl.setWords(wordListDto.getWords());
        wordListRepository.save(wl);

        return wordListDto;
    }

    @Override
    public void deleteWordList(String title) {
        wordListRepository.deleteById(title);
    }


    @Override
    public WordListDto updateWordList(String title, WordListDto wordListDto) {
        if (wordListRepository.findById(title).isPresent()) {

            WordList wl = wordListRepository.findById(title).get();
            wl.setTitle(wordListDto.getTitle());
            wl.setWords(wordListDto.getWords());
            wordListRepository.save(wl);

            return wordListDto;
        } else {
            throw new RecordNotFoundException("Geen woordenlijst gevonden");
        }
    }

//    @Override
//    public void deleteWordsInWordList(List<String> words) {
//        wordListRepository.deleteAllById(words);
//    }
//    Deze doet het nog niet helaas

}
