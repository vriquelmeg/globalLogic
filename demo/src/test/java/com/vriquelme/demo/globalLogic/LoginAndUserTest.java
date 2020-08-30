package com.vriquelme.demo.globalLogic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vriquelme.demo.model.dto.LoginDTO;
import com.vriquelme.demo.model.dto.PhoneDTO;
import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.exception.EmailIsNullException;
import com.vriquelme.demo.model.exception.EmailNotFormatException;
import com.vriquelme.demo.model.exception.PasswordIsNullException;
import com.vriquelme.demo.model.exception.PasswordNotCorrectFormatException;
import com.vriquelme.demo.model.helper.LoginHelper;
import com.vriquelme.demo.model.repository.UserRepository;
import com.vriquelme.demo.model.services.LoginServices;
import com.vriquelme.demo.model.services.UserServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginAndUserTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginAndUserTest.class);

    @Mock
    private LoginHelper loginHelper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoginServices loginServices;

    @Mock
    private UserServices userServices;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.loginHelper = new LoginHelper(loginServices, userServices, objectMapper);
    }

    @Test
    public void loginSuccessfulTest() {
        try {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setEmail("vriquelmeg@gmail.com");
            loginDTO.setPassword("12345");

            UUID uuid = UUID.randomUUID();

            PhoneDTO phoneDTO = new PhoneDTO();
            phoneDTO.setPhone(44554333);
            phoneDTO.setCountryCode(3);
            phoneDTO.setCityCode(2);

            ArrayList<PhoneDTO> phoneDTOS = new ArrayList<>();
            phoneDTOS.add(phoneDTO);

            UserDTO userDTO = new UserDTO();
            userDTO.setId(uuid);
            userDTO.setName("Victor Riquelme");
            userDTO.setEmail("vriquelmeg@gmail.com");
            userDTO.setDateCreate(LocalDateTime.now());
            userDTO.setDateModified(LocalDateTime.now());
            userDTO.setLastLogin(LocalDateTime.now());
            userDTO.setIsActive(true);
            userDTO.setToken("");
            userDTO.setPassword("12345");
            userDTO.setPhonesList(phoneDTOS);

            when(loginServices.getLoginUser(loginDTO)).thenReturn(userDTO);
            when(objectMapper.writeValueAsString(userDTO)).thenReturn("{\"id\":\"f407c0b2-579a-48f5-941a-1899d78cb33b\",\"name\":\"Victor Riquelme\",\"email\":\"vriquelmeg@gmail.com\",\"phonesList\":null,\"phoneJson\":\"[{\\\"countryCode\\\":56,\\\"cityCode\\\":2},{\\\"countryCode\\\":56,\\\"cityCode\\\":2}]\",\"dateCreate\":\"2020-08-30T11:27:29.458\",\"dateModified\":\"2020-08-30T11:27:29.458\",\"lastLogin\":\"2020-08-30T11:27:29.459\",\"isActive\":true,\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2cmlxdWVsbWVnQGdtYWlsLmNvbSJ9.zMgjDQvMvIAlDYsMQRh83K7z_0cUdBkGFhewwgrx4yg\",\"password\":\"Victor12\"}");

            String response = loginHelper.getLogin(loginDTO);

            Assert.assertNotNull(response);
        } catch (JsonProcessingException e) {
            logger.error(e.toString());
        }

    }

    @Test(expected = EmailIsNullException.class)
    public void loginExceptionFailedEmailNullTest(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(null);
        loginDTO.setPassword("12345");
        String response = loginHelper.getLogin(loginDTO);

        Assert.assertNull(response);
    }

    @Test(expected = EmailNotFormatException.class)
    public void loginExceptionFailedEmailNotFormatTest(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("vriquelmegmail.com");
        loginDTO.setPassword("11111");
        String response = loginHelper.getLogin(loginDTO);

        Assert.assertNull(response);
    }

    @Test(expected = PasswordIsNullException.class)
    public void loginExceptionFailedPasswordIsNullTest(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("vriquelme@gmail.com");
        loginDTO.setPassword("");
        String response = loginHelper.getLogin(loginDTO);

        Assert.assertNull(response);
    }
}
