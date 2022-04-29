package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
