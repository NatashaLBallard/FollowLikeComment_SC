package com.followlikecomment_sc.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");


        //Create Roles
        RoleClass role = new RoleClass();
        role.setRole("USER");
        roleRepository.save(role);

        role = new RoleClass();
        role.setRole("ADMIN");
        roleRepository.save(role);


        //Create Users
        System.out.println("Loading users...");
        User user = new User();
//        user.setPassword(passwordEncoder.encode("password"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setUsername(user.getUsername());
        user.addRole(roleRepository.findByRole("USER"));
        user.addRole(roleRepository.findByRole("ADMIN"));
        userRepository.save(user);
//        roleRepository.save(new RoleClass("USER"));
//        RoleClass userRole = roleRepository.findByRole("USER");




//        roleRepository.save(new RoleClass("ADMIN"));
//       roleRepository.save(new RoleClass("USER"));


//        RoleClass adminRole = roleRepository.findByRole("ADMIN");
//        RoleClass userRole = roleRepository.findByRole("USER");

//
//        User user = new User();
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//          user.setEmail(user.getEmail());
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);












        user = new User("test@tes.com", "test", "Test", "TestLastName", true, "user", "USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByRole("USER"));
        userRepository.save(user);


        user = new  User("admin@adm.com", "password", "Admin", "AdminLastName", true, "admin", "ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByRole("ADMIN"));
        userRepository.save(user);





    }
}