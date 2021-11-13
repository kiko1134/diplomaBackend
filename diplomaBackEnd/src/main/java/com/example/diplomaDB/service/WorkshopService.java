package com.example.diplomaDB.service;

import com.example.diplomaDB.model.Workshop;

import java.util.List;

public interface WorkshopService {

    List<Workshop> getWorkshops();

    void addNewWorkshop(Workshop workshop);

    void deleteWorkshop(Long workshop_id);

    void updateWorkshop(Long workshopId, String name, String email, String phone, String address, String description);
}
