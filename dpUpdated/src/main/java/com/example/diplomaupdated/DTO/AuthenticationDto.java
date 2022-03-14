package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class AuthenticationDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
