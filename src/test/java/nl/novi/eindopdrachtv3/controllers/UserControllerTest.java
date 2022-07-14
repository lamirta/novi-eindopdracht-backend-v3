package nl.novi.eindopdrachtv3.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;


import java.util.HashSet;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.models.Authority;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.services.CustomUserDetailsService;
import nl.novi.eindopdrachtv3.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;


    @Test
    void testCreateUser() throws Exception {
        when(this.userServiceImpl.createUser((UserDto) any()))
                .thenReturn(new UserDto("janedoe", "password", "jane.doe@test.com"));

        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        User user = new User();
        user.addAuthority(new Authority());
        user.setEmail("jane.doe@test.com");
        user.setEnabled(true);
        user.setPassword("password");
        user.setUserProfile(new UserProfile());
        user.setUsername("janedoe");

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("Jane");
        userProfile.setId(123L);
        userProfile.setUsername(user);

        UserDto userDto = new UserDto();
        userDto.setAuthorities(new HashSet<>());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(true);
        userDto.setPassword("password");
        userDto.setUserProfile(userProfile);
        userDto.setUsername(user.getUsername());

        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User aangemaakt!"));
    }


    @Test
    void testSetUserEnabled() throws Exception {
        doNothing().when(this.userServiceImpl)
                .setUserEnabled((String) any(), (UserDto) any());

        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        User user = new User();
        user.addAuthority(new Authority());
        user.setEmail("jane.doe@test.com");
        user.setEnabled(true);
        user.setPassword("password");
        user.setUserProfile(new UserProfile());
        user.setUsername("janedoe");

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("Jane");
        userProfile.setId(123L);
        userProfile.setUsername(user);

        UserDto userDto = new UserDto();
        userDto.setAuthorities(new HashSet<>());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(true);
        userDto.setPassword("password");
        userDto.setUserProfile(userProfile);
        userDto.setUsername(user.getUsername());

        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{username}/enabled", "janedoe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}

