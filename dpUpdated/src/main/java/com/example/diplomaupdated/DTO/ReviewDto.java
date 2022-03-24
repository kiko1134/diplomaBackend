package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ReviewDto {
    @NotNull
    private String user_id;
    @NotNull
    private String workshop_id;
    @NotNull
    private String content;
}
