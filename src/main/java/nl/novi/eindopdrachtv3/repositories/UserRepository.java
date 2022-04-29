package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
