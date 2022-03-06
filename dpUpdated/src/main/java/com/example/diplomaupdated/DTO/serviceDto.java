package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class serviceDto{
    @NotNull
    private String name;

    @NotNull
    private String price;
}
