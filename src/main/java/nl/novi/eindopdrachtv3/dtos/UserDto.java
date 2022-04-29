package nl.novi.eindopdrachtv3.dtos;

import javax.persistence.Column;

public class UserDto {

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false, length = 35)
    public String password;

    @Column(nullable = false)
    public String email;

    public Boolean enabled;
    public String apikey;

    // @OneToOne ???
    // private UserData userData;

//    @OneToMany(
//            targetEntity = Authority.class,
//            mappedBy = "username",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.EAGER)
//    private Set<Authority> authorities = new HashSet<>();


    public UserDto() {
    }

    public UserDto(String username, String password, String email, Boolean enabled, String apikey) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.apikey = apikey;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getApikey() {
        return apikey;
    }
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

}
