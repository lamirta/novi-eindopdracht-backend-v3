package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDataDto;
import nl.novi.eindopdrachtv3.repositories.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService{

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public UserDataDto getAllUserData() {
        return null;
    }

}
