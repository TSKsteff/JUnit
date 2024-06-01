package br.com.univali.apiteste.services.impl;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.repositories.UserRepository;
import br.com.univali.apiteste.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
