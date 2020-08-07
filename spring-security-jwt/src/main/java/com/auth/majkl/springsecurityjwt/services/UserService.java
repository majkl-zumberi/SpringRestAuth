package com.auth.majkl.springsecurityjwt.services;

import com.auth.majkl.springsecurityjwt.entities.User;
import com.auth.majkl.springsecurityjwt.exceptions.MZAuthException;

import java.util.Optional;

public interface UserService {
    User validateUser(String email, String password) throws  MZAuthException;
    User signUpUser(User entity) throws MZAuthException;
}
