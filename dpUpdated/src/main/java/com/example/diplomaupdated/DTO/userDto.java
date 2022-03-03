package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class userDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
