package com.sisprog.keyboard.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    @ApiModelProperty(notes = "Username of the user", name = "username", required = true, value = "user123", example = "user123")
    private String username;
    @ApiModelProperty(notes = "Password of the user", name = "password", required = true, value = "pass123", example = "pass123")
    private String password;
}
