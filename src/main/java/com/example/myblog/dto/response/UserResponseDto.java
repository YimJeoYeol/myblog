package com.example.myblog.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserResponseDto {

    private Long id;
    @NotBlank
    @Size(min =4, max = 12)
    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])")
    private String password;

}
