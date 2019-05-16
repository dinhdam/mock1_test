package com.itsol.mock1.service;

import com.itsol.mock1.model.User;


import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import java.util.List;
@Service
public interface UserService extends Validator {
    List<User> findAll();

    User findById(int id);

    User findUserByEmail(String email);

    User findUserByUserName(String name);

    User findUserByUsernameAndPassword(String username, String password);

    void save(User user);

    void deleteById(int id);

    boolean confirmUser(User user);
}
