package com.itsol.mock1.repository;

import com.itsol.mock1.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String name);

}
