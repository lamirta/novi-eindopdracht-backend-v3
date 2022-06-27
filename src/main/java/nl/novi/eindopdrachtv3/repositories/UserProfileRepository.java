package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
//    UserProfile findByUsername(User username);
//    UserProfile findUserProfilesByUsernameIs(String username);
//    UserProfile findAllByUsername_Username(String username);
    UserProfile findByUsername(String username);
//    UserProfile getByUserId (String username);
//    UserProfile findByUsername(String username);
//      public UserProfile findByUserUsername(String username);
}
