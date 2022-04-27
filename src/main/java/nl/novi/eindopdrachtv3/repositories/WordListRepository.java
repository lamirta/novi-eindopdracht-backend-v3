package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.WordList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordListRepository extends JpaRepository<WordList, String> {

}
