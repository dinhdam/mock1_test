package com.itsol.mock1.config;

import com.itsol.mock1.model.Role;
import com.itsol.mock1.repository.RoleRepository;
import com.itsol.mock1.repository.UserRepository;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
//public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
////    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//        if (roleRepository.findByName("ROLE_ADMIN") == null) {
//            roleRepository.save(new Role());
//        }
//        if (roleRepository.findByName("ROLE_MENBER") == null) {
//            roleRepository.save(new Role());
//        }
//
//    }
//}
