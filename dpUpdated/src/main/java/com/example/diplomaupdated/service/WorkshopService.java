package com.example.diplomaupdated.service;

import com.example.diplomaupdated.DTO.workshopDto;
import com.example.diplomaupdated.model.Workshop;

import java.util.List;

public interface WorkshopService {
    List<Workshop> getWorkshops();
    void registerNewWorkshop(workshopDto workshopDto);
    void deleteWorkshop(Long workshopId);
    void updateWorkshop(Long id, String name, String email, String password, String workshop_address, String phone_number, String workshop_description);
    void addServiceToWorkshop(Long workshopId, Long serviceId, Double price);
}
