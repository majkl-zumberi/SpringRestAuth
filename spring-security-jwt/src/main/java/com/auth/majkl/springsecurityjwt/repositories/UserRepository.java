package com.auth.majkl.springsecurityjwt.repositories;

import com.auth.majkl.springsecurityjwt.entities.User;
import com.auth.majkl.springsecurityjwt.exceptions.MZAuthException;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    public Integer countByEmail(String email);
    public User findByEmailAndPassword(String email,String password);
    public User findByEmail(String email);
}
