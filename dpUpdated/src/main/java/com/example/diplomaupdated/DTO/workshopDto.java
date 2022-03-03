package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class workshopDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phone_number;

    @NotNull
    private String workshop_address;

    private String workshop_description;

}
