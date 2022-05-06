package nl.novi.eindopdrachtv3.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Lob
    public byte[] image;

    private String type;

//    @OneToOne(cascade = CascadeType.ALL) //moet dit erachter?
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserData user;
// uitzoek of dit welke precies bij student en welke bij image moet staan. is het bidirectional of niet?

    public Image() {
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
