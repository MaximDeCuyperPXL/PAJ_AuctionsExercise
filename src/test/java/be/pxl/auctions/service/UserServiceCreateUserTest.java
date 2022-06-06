package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserRepository;
import be.pxl.auctions.model.User;
import be.pxl.auctions.rest.resource.UserCreateResource;
import be.pxl.auctions.util.exception.DuplicateEmailException;
import be.pxl.auctions.util.exception.InvalidDateException;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.RequiredFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceCreateUserTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

	// TODO add unit tests for all possible scenario's of the createUser method
    @Test
    public void throwsRequiredFieldExceptionWhenFirstNameIsBlank(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setLastName("Zuckerberg");
        newUser.setDateOfBirth(String.valueOf(LocalDate.of(1989, 5, 3)));
        newUser.setEmail("mark@facebook.com");
        RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> userService.createUser(newUser));
        assertEquals("[FirstName] is required.", exception.getMessage());
    }

    @Test
    public void throwsRequiredFieldExceptionWhenLastNameIsBlank(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setDateOfBirth(String.valueOf(LocalDate.of(1989, 5, 3)));
        newUser.setEmail("mark@facebook.com");
        RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> userService.createUser(newUser));
        assertEquals("[LastName] is required.", exception.getMessage());
    }

    @Test
    public void throwsRequiredFieldExceptionWhenEmailIsBlank(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setLastName("Zuckerberg");
        newUser.setDateOfBirth(String.valueOf(LocalDate.of(1989, 5, 3)));
        RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> userService.createUser(newUser));
        assertEquals("[Email] is required.", exception.getMessage());
    }

    @Test
    public void throwsInvalidEmailExceptionWhenEmailIsInvalid(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setLastName("Zuckerberg");
        newUser.setDateOfBirth(String.valueOf(LocalDate.of(1989, 5, 3)));
        newUser.setEmail("mark.facebook.com");
        InvalidEmailException exception = assertThrows(InvalidEmailException.class, () -> userService.createUser(newUser));
        assertEquals("[mark.facebook.com] is not a valid email.", exception.getMessage());
    }

    @Test
    public void throwsRequiredFieldExceptionWhenDateOfBirthIsBlank(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setLastName("Zuckerberg");
        newUser.setEmail("mark@facebook.com");
        RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> userService.createUser(newUser));
        assertEquals("[DateOfBirth] is required.", exception.getMessage());
    }

    @Test
    public void throwsDuplicateEmailExceptionWhenUserEmailAlreadyExists(){
        User user = new User();
        user.setFirstName("Mark");
        user.setLastName("Zuckerberg");
        user.setEmail("mark@facebook.com");
        when(userRepository.findUserByEmail("mark@facebook.com")).thenReturn(Optional.of(user));
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setLastName("Zuckerberg");
        newUser.setEmail("mark@facebook.com");
        newUser.setDateOfBirth(String.valueOf(LocalDate.of(1989, 5, 3)));
        DuplicateEmailException exception = assertThrows(DuplicateEmailException.class, () -> userService.createUser(newUser));
        assertEquals("Email [mark@facebook.com] is already in use.", exception.getMessage());
    }

    @Test
    public void throwsInvalidDateExceptionWhenDateOfBirthIsInTheFuture(){
        UserCreateResource newUser = new UserCreateResource();
        newUser.setFirstName("Mark");
        newUser.setLastName("Zuckerberg");
        newUser.setDateOfBirth("03/05/9999");
        newUser.setEmail("mark@facebook.com");
        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> userService.createUser(newUser));
        assertEquals("DateOfBirth cannot be in the future.", exception.getMessage());
    }
}
