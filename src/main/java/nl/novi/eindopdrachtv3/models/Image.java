package nl.novi.eindopdrachtv3.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @UniqueElements
    private Long id;

    private String imageName;

    @Lob
    public byte[] image;

    private String contentType;

//    @OneToOne(cascade = CascadeType.ALL) //moet dit erachter?
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserData user;
// uitzoek of dit welke precies bij student en welke bij image moet staan. is het bidirectional of niet?

    public Image() {
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
