package br.com.univali.apiteste.services.impl;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.domain.dto.UserDTO;
import br.com.univali.apiteste.repositories.UserRepository;
import br.com.univali.apiteste.services.exceptions.DataIntegratyViolationException;
import br.com.univali.apiteste.services.exceptions.ObjectNotFoundException;
import com.sun.jdi.event.ExceptionEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "steff";
    public static final String EMAIL = "steff@gmail.com";
    public static final String PASSWORD = "12453";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail ja cadastrado no sistema";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userServiceImpl.findById(ID);
        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(User.class, response.getClass());
    }


    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            userServiceImpl.findById(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
        }
    }
    @Test
    void whenFindAllThenReturnAnListUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userServiceImpl.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(userRepository.save(any()))
                .thenReturn(user);
        User userResponse = userServiceImpl.create(userDTO);

        assertNotNull(userResponse);
        assertEquals(userResponse.getClass(), User.class);
        assertEquals(ID, userResponse.getId());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(optionalUser);
        try{
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }catch (Exception e){
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(userRepository.save(any()))
                .thenReturn(user);
        User userResponse = userServiceImpl.update(userDTO);

        assertNotNull(userResponse);
        assertEquals(userResponse.getClass(), User.class);
        assertEquals(ID, userResponse.getId());
    }


    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationExeption() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(optionalUser);
        try{
            userServiceImpl.create(userDTO);
        }catch (Exception e){
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
        }

    }
    @Test
    void deleteWithSuccess() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userServiceImpl.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try{
            userServiceImpl.delete(ID);
        }catch (Exception ex){
                assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
                assertEquals(ObjectNotFoundException.class, ex.getClass());
        }
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID,NAME,EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}