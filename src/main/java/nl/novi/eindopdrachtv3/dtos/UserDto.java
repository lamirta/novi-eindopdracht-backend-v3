package nl.novi.eindopdrachtv3.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.eindopdrachtv3.models.Authority;

import javax.persistence.Column;
import java.util.Set;

public class UserDto {

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false, length = 35)
    public String password;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public boolean enabled;

    @JsonSerialize
    public Set<Authority> authorities;

    // @OneToOne ???
    // private UserData userData;



    public UserDto() {
    }

    public UserDto(String username, String password, String email, boolean enabled, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
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

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
