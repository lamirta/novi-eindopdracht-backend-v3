package nl.novi.eindopdrachtv3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Long id;

    @Lob
    public byte[] image;

    private String type;

    @OneToOne(mappedBy = "profilePic")
    @JsonIgnore
    private UserProfile userProfile;

    public Image() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}



//Of andersom?
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
//    private UserProfile userProfile;