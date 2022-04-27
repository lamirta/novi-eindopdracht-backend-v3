package nl.novi.eindopdrachtv3.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wordlists")
public class WordList {

    @Id
    private String title;

    @ElementCollection
    private List<String> words = new ArrayList<String>();

//    //OneToMany relation
//    private List<Exam> exams;

}
