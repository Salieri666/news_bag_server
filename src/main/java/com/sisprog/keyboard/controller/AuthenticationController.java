package com.sisprog.keyboard.controller;

import com.sisprog.keyboard.dao.UserDao;
import com.sisprog.keyboard.domain.User;
import com.sisprog.keyboard.dto.AuthRequestDto;
import com.sisprog.keyboard.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDao userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDao userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDto requestDto) {
        if (requestDto != null) {
            try {
                String username = requestDto.getUsername();
                User user = userService.findByUsername(username);

                if (user == null) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

                String token = jwtTokenProvider.createToken(username, user.getRoleList());

                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("token", "Bearer_" + token);
                response.put("id", user.getId());

                return ResponseEntity.ok(response);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else
            return null;
    }
}
