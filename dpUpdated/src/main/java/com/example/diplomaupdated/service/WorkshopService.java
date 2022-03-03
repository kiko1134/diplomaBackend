package com.example.diplomaupdated.service;

import com.example.diplomaupdated.model.Workshop;

import java.util.List;

public interface WorkshopService {
    List<Workshop> getWorkshops();
    void addNewWorkshop(Workshop workshop);
    void deleteWorkshop(Long WworkshopId);
    void updateWorkshop(Long id, String name, String email, String password, String workshop_address, String phone_number, String workshop_description);

}
