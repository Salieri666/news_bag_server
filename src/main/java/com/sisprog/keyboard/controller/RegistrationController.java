package com.sisprog.keyboard.controller;


import com.sisprog.keyboard.dao.UserDao;
import com.sisprog.keyboard.domain.User;
import com.sisprog.keyboard.dto.AuthRequestDto;
import com.sisprog.keyboard.security.JwtTokenProvider;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public RegistrationController(UserDao userRepository,
                                  PasswordEncoder passwordEncoder,
                                  AuthenticationManager authenticationManager,
                                  JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<Map<Object, Object>> reg(@RequestBody AuthRequestDto requestDto) {
        if (requestDto != null) {
            try {
                User user = userRepository.findByUsername(requestDto.getUsername());

                if (user != null) {
                    throw new UsernameNotFoundException("User with username: " + requestDto.getUsername() + " exists!");
                }

                User newuser = new User();
                newuser.setUsername(requestDto.getUsername());
                newuser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
                newuser.setRoles("ADMIN");
                newuser.setPermissions("");
                newuser.setActive(1);

                User savedUser = userRepository.save(newuser);

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(savedUser.getUsername(), requestDto.getPassword()));
                String token = jwtTokenProvider.createToken(savedUser.getUsername(), savedUser.getRoleList());

                Map<Object, Object> response = new HashMap<>();
                response.put("username", savedUser.getUsername());
                response.put("token", "Bearer_" + token);
                response.put("id", savedUser.getId());

                return ResponseEntity.ok(response);
            } catch (DataAccessException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } catch (UsernameNotFoundException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            } catch (Exception | Error e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return null;
        }
    }
}
