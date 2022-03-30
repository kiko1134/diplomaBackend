package com.example.diplomaupdated.service;

import com.example.diplomaupdated.DTO.WorkshopDto;
import com.example.diplomaupdated.model.Workshop;

import java.util.List;

public interface WorkshopService {
    List<Workshop> getWorkshops();
    void registerNewWorkshop(WorkshopDto workshopDto);
    void deleteWorkshop(Long workshopId);
    void updateWorkshop(Long id, String name, String email, String workshop_address, String phone_number, String workshop_description);
    void addServiceToWorkshop(Long workshopId, String serviceId, Double price);
    void updateServiceToWorkshop(Long workshopId, String serviceId, Double price);
    List<com.example.diplomaupdated.model.WorkshopService> getWorkshopServicesServiceId(String serviceId);
    List<com.example.diplomaupdated.model.WorkshopService> getWorkshopServicesWorkshopName(String worksopName);
}
