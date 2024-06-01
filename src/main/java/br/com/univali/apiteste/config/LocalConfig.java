package br.com.univali.apiteste.config;

import br.com.univali.apiteste.domain.User;
import br.com.univali.apiteste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "steff", "steff@mail.com", "123");
        User u2 = new User(null, "piard", "piard@mail.com", "12ss3");

        userRepository.saveAll(List.of(u1,u2));
    }
}
