package com.ryo.medicineservice.configuration;

import com.ryo.medicineservice.entity.User;
import com.ryo.medicineservice.enums.Role;
import com.ryo.medicineservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .roles(roles)
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
                log.warn("Admin user have been created with default password admin");
            }
        };
    }
}
