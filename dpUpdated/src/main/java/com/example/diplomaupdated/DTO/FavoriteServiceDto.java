package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class FavoriteServiceDto {
    @NotNull
    private String workshop_id;
    @NotNull
    private String service_id;
    @NotNull
    private String user_id;
}
