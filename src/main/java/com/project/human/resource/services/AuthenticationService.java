package com.project.human.resource.services;

import com.project.human.resource.domain.AuthenticationException;
import com.project.human.resource.models.User;
import com.project.human.resource.presentation.LoginForm;
import com.project.human.resource.presentation.RegistrationForm;
import com.project.human.resource.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegistrationForm registrationInfo) throws AuthenticationException{
        User newUser = userRepository.findByName(registrationInfo.getName());
        if(newUser != null) {
            throw new AuthenticationException("The user " + registrationInfo.getName() + " already exists!");
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String EncryptPass = encoder.encode(registrationInfo.getPassword());
        registrationInfo.setPassword(EncryptPass);
        newUser = new User(registrationInfo.getName(), registrationInfo.getRole(), registrationInfo.getPassword());
        userRepository.save(newUser);
    }

    public User loginUser(LoginForm loginInfo) throws AuthenticationException {
        User userInfo = userRepository.findByName(loginInfo.getName());
        if(userInfo == null) {
            throw new AuthenticationException("User " + loginInfo.getName() + " does not exist!");
        }
        if(!BCrypt.checkpw(loginInfo.getPassword(), userInfo.getPassword())) {
            throw new AuthenticationException("Password does not match!");
        }
        return userInfo;
    }
}
