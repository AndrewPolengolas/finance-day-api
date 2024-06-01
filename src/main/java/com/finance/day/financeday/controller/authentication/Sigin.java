package com.finance.day.financeday.controller.authentication;

import com.finance.day.financeday.entities.User;
import com.finance.day.financeday.records.auth.SigninRecord;
import com.finance.day.financeday.repositories.users.UserRepository;
import com.finance.day.financeday.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/signin")
@AllArgsConstructor
public class Sigin {

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    @PostMapping
    public ResponseEntity<?> sigini(@RequestBody @Validated SigninRecord signinRecord){

        User user = userRepository.findByUsernameOrEmail(signinRecord.username(), signinRecord.email());


        if (user == null){
            user = new User();
            user.setUsername(signinRecord.username());
            user.setEmail(signinRecord.email());
            user.setPassword(securityConfig.passwordEncoder().encode(signinRecord.password()));
            user.setCreatedAt(new Date());

            userRepository.save(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
