package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ServiceCreateDto {
    @NotNull
    private String name;
}
