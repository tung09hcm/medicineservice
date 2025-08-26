package com.ryo.medicineservice.configuration;

import com.ryo.medicineservice.entity.User;
import com.ryo.medicineservice.entity.Role;
import com.ryo.medicineservice.repository.RoleRepository;
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
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<Role>();
                roleRepository.save(Role.builder()
                        .name(com.ryo.medicineservice.enums.Role.USER.name())
                        .description("User role")
                        .build());
                Role role = roleRepository.save(Role.builder()
                        .name(com.ryo.medicineservice.enums.Role.ADMIN.name())
                        .description("Admin role")
                        .build());
                roles.add(role);
                User user = User.builder()
                        .username("admin")
                        .roles(roles)
                        .deleted(false)
                        .password(passwordEncoder.encode("admin"))
                        .build();
//                log.info("Admin information: " + user.toString());
                roles.forEach(x -> {log.info(x.getName());});
                userRepository.save(user);
                log.warn("Admin user have been created with default password admin");
            }
        };
    }
}
