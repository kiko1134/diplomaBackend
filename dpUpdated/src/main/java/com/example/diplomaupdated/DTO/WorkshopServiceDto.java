package com.example.diplomaupdated.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class WorkshopServiceDto {
    @NotNull
    private String workshop_id;
    @NotNull
    private String service_name;
    @NotNull
    private String price;
}
