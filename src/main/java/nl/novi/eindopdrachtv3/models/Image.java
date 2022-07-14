package nl.novi.eindopdrachtv3.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Image {

    @Id
    @Column(unique = true)
    private String fileName;

    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "image_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    private String type;

    private String url;

    public Image() {
    }

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image(String fileName, String type, String url) {
        this.fileName = fileName;
        this.type = type;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
