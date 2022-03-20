package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class WorkshopResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String token;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String phone_number;
    @NotNull
    private String workshop_address;
    @NotNull
    private String workshop_description;
    @NotNull
    Collection<? extends GrantedAuthority> authorities;

    public WorkshopResponseDto(Long id,String token, String username, String email, String phone_number, String workshop_address,
                               String workshop_description, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.workshop_address = workshop_address;
        this.workshop_description = workshop_description;
        this.authorities = authorities;
    }
}
