package com.auth.majkl.springsecurityjwt.services;

import com.auth.majkl.springsecurityjwt.entities.User;
import com.auth.majkl.springsecurityjwt.exceptions.MZAuthException;
import com.auth.majkl.springsecurityjwt.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional//if any transition fail, rollback will be executed
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    public User validateUser(String email, String password) throws MZAuthException {
        if(email != null) email.toLowerCase();//case insensitive
        User user= userRepository.findByEmail(email);
        if(user == null)
            throw new MZAuthException("nessun utente trovato");
        if(!BCrypt.checkpw(password,user.getPassword()))
            throw new MZAuthException("password errata");
        if(userRepository.findByEmailAndPassword(email, user.getPassword()) == null)
            throw new MZAuthException("email/password invalida");
        return userRepository.findByEmailAndPassword(email, user.getPassword());
    }

    @Override
    public User signUpUser(User entity) throws MZAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String email= entity.getEmail();

        if(email != null) email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw  new MZAuthException("formato email non valido");
        Integer count= userRepository.countByEmail(email);
        if(count>0)
            throw  new MZAuthException("email già in uso");
        String hashedPassword= BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt(10));
        entity.setPassword(hashedPassword);
        userRepository.save(entity);
        Integer id= entity.getId();
        return userRepository.findById(id).orElse(null);
    }

}
