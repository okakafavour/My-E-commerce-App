package org.example.service;


import org.example.dto.response.RegisterResponse;
import org.example.enums.Role;
import org.example.data.repository.UserRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }


    @Test
    public void testToRegisterAndLoginAsACustomer(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Musa");
        registerRequest.setLastName("Ijidola");
        registerRequest.setEmail("kim24@gmail.com");
        registerRequest.setPassword("12345");
        registerRequest.setPhoneNumber("09087654321");
        registerRequest.setRole(Role.CUSTOMER);
        registerRequest.setEnable(true);
        userService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kim24@gmail.com");
        loginRequest.setPassword("12345");
        userService.login(loginRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterAndLoginAsASeller(){
        RegisterRequest registerRequest  =  new RegisterRequest();
        registerRequest.setFirstName("Abimbola");
        registerRequest.setLastName("Isreal");
        registerRequest.setPhoneNumber("07125346789");
        registerRequest.setEmail("Isreal12@gmail.com");
        registerRequest.setPassword("precious@123#");
        registerRequest.setRole(Role.VENDOR);
        RegisterResponse response = userService.register(registerRequest);
        assertEquals("Registered Successfully", response.getMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Isreal12@gmail.com");
        loginRequest.setPassword("precious@123#");
         userService.login(loginRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterAndLoginAsAnAdmin(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Gbenga");
        registerRequest.setLastName("tayo");
        registerRequest.setPhoneNumber("08123456789");
        registerRequest.setEmail("tayo12@gmail.com");
        registerRequest.setEnable(true);
        registerRequest.setPassword("tayo1234");
        registerRequest.setRole(Role.ADMIN);
        RegisterResponse response = userService.register(registerRequest);
        assertEquals("Registered Successfully", response.getMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("tayo12@gmail.com");
        loginRequest.setPassword("tayo1234");
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatRegisterWithWrongNumber_ThrowsAnError(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("thinkerbell");
        registerRequest.setLastName("tayo");
        registerRequest.setPhoneNumber("0812345678");
        registerRequest.setEmail("bby12@gmail.com");
        registerRequest.setEnable(true);
        registerRequest.setPassword("tayo1234");
        registerRequest.setRole(Role.ADMIN);

        Exception exception = assertThrows(InvalidPhoneNumber.class, () -> {
            userService.register(registerRequest);
        });
        assertEquals("Invalid phone number", exception.getMessage());
    }

    @Test
    public void testThatRegisterWithWrongEmail_ThrowsAnError(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Ibrahim ibrahim");
        registerRequest.setLastName("babatunde");
        registerRequest.setPhoneNumber("09876543212");
        registerRequest.setEmail("invalid-email@@");
        registerRequest.setEnable(true);
        registerRequest.setPassword("bram1234");
        registerRequest.setRole(Role.ADMIN);

        Exception exception = assertThrows(InvalidEmailException.class, ()-> {
           userService.register(registerRequest);
        });
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    public void testThatRegisterWithWrongNameInput_ForFirstName_ThrowsError(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Niko1212");
        registerRequest.setLastName("john");
        registerRequest.setPhoneNumber("09870543212");
        registerRequest.setEmail("niko65@gmail.com");
        registerRequest.setEnable(true);
        registerRequest.setPassword("niko1111");
        registerRequest.setRole(Role.ADMIN);

        Exception exception = assertThrows(InvalidNameException.class, ()-> {
            userService.register(registerRequest);
        });
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    public void testThatRegisterWithWrongNameInput_ForLastName_ThrowError(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("james");
        registerRequest.setLastName("brown1212");
        registerRequest.setPhoneNumber("09870543212");
        registerRequest.setEmail("brown05@gmail.com");
        registerRequest.setEnable(true);
        registerRequest.setPassword("brown9090");
        registerRequest.setRole(Role.ADMIN);

        Exception exception = assertThrows(InvalidNameException.class, ()-> {
            userService.register(registerRequest);
        });
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    public void testThatRegisterAndLoginWithInvalidPassword_ThrowsAnError(){
        RegisterRequest registerRequest  =  new RegisterRequest();
        registerRequest.setFirstName("jadesola");
        registerRequest.setLastName("monisola");
        registerRequest.setPhoneNumber("07125346789");
        registerRequest.setEmail("muiz42@gmail.com");
        registerRequest.setPassword("muiz1234");
        registerRequest.setRole(Role.VENDOR);
       userService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("muiz42@gmail.com");
        loginRequest.setPassword("muiz12345");

        assertThrows(InvalidPasswordException.class, () -> {
            userService.login(loginRequest);
        });
    }

    @Test
    public void testThatLoginWithInvalidEmail_ThrowsAnError(){
        RegisterRequest registerRequest  =  new RegisterRequest();
        registerRequest.setFirstName("suliat");
        registerRequest.setLastName("yusuf");
        registerRequest.setPhoneNumber("07125096789");
        registerRequest.setEmail("yusuf62@gmail.com");
        registerRequest.setPassword("yusuf222");
        registerRequest.setRole(Role.VENDOR);
        registerRequest.setEnable(true);
        userService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("yusuf400@gmail.com");
        loginRequest.setPassword("yusuf222");

        assertThrows(UserNotFoundException.class, () -> {
            userService.login(loginRequest);
        });
    }
}