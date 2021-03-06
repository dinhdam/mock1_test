package com.itsol.mock1.repository;

import com.itsol.mock1.model.Role;
import com.itsol.mock1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByUsername(String username);

    public User findUserByEmail(String email);

    public User findUserByUsernameAndPassword(String username, String password);

    User findByEmail(String email);


}