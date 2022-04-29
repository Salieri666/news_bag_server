package com.sisprog.keyboard.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    @ApiModelProperty(notes = "Username of the user", name = "username", required = true, value = "user123", example = "user123")
    private String username;
    @ApiModelProperty(notes = "Access token", name = "token", required = true, value = "Bearer_token", example = "Bearer_token")
    private String token;
    @ApiModelProperty(notes = "User id", name = "id", required = true, value = "123", example = "123")
    private Long id;
}
