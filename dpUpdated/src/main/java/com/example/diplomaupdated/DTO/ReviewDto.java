package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ReviewDto {
    @NotNull
    private String id;
    @NotNull
    private String role;
    @NotNull
    private String content;
}
