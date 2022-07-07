package nl.novi.eindopdrachtv3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(unique = true)
    private String fileName;

    @GeneratedValue
    @Column(unique = true)
    private Long id;

    private String type;

    private String url;

//    @Lob
//    public byte[] image;


    @OneToOne(mappedBy = "profilePic")
    @JsonIgnore
    private UserProfile userProfile;

    public Image() {
    }

    public Image(Long id) {
        this.id = id;
    }

    public Image(Long id, String fileName, String type, String url, UserProfile userProfile) {
        this.id = id;
        this.fileName = fileName;
        this.type = type;
        this.url = url;
        this.userProfile = userProfile;
    }

    public Image(Long id, String fileName, String type, String url) {
        this.id = id;
        this.fileName = fileName;
        this.type = type;
        this.url = url;
    }

    public Image(String fileName, String type, String url) {
        this.fileName = fileName;
        this.type = type;
        this.url = url;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    //    public byte[] getImage() {
//        return image;
//    }
//    public void setImage(byte[] image) {
//        this.image = image;
//    }


}



//Of andersom?
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
//    private UserProfile userProfile;

// Try out..
//    public Image(Long id, byte[] image, String type, UserProfile userProfile) {
//        this.id = id;
//        this.image = image;
//        this.type = type;
//        this.userProfile = userProfile;
//    }
