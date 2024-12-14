package br.com.univali.apiteste.services;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

     User findById(Integer id);
     List<User> findAll();
     User create(UserDTO userDTO);
     User update(UserDTO userDTO);
     void delete(Integer id);
}
