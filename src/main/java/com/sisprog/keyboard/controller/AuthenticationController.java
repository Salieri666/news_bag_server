package com.sisprog.keyboard.controller;

import com.sisprog.keyboard.config.SwaggerConfig;
import com.sisprog.keyboard.dao.UserDao;
import com.sisprog.keyboard.domain.User;
import com.sisprog.keyboard.dto.AuthRequestDto;
import com.sisprog.keyboard.dto.ResponseDto;
import com.sisprog.keyboard.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "AuthenticationController", tags = {SwaggerConfig.AUTH_TAG})
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

    @ApiOperation(value = "Login for existing user", response = ResponseDto.class)
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "requestDto", paramType = "AuthRequestDto", dataType = "AuthRequestDto", required = true, type = "AuthRequestDto", value = "Login for users")}
    )
    @PostMapping()
    public ResponseEntity<ResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        if (requestDto != null) {
            try {
                String username = requestDto.getUsername();
                User user = userService.findByUsername(username);

                if (user == null) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

                String token = jwtTokenProvider.createToken(username, user.getRoleList());
                ResponseDto responseDto = new ResponseDto(username, "Bearer_" + token, user.getId());

                return ResponseEntity.ok(responseDto);
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
