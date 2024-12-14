package br.com.univali.apiteste.services.impl;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.domain.dto.UserDTO;
import br.com.univali.apiteste.repositories.UserRepository;
import br.com.univali.apiteste.services.UserService;
import br.com.univali.apiteste.services.exceptions.DataIntegratyViolationException;
import br.com.univali.apiteste.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("usuario não encontrado"));
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO){
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
         userRepository.deleteById(id);
    }

    public void findByEmail(UserDTO userDTO){
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent() && !user.get().getId().equals(userDTO.getId())){
            throw new DataIntegratyViolationException("E-mail ja cadastrado no sistema");
        }
    }
}
