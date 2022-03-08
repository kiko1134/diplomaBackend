package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class WorkshopDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phone_number;

    @NotNull
    private String workshop_address;

    @NotNull
    private String workshop_description;

    @NotNull
    private String role;

}
