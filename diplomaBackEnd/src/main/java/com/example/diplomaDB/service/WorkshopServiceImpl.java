package com.example.diplomaDB.service;

import com.example.diplomaDB.model.User;
import com.example.diplomaDB.model.Workshop;
import com.example.diplomaDB.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WorkshopServiceImpl implements WorkshopService{

    private final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public List<Workshop> getWorkshops() {
        return workshopRepository.findAll();
    }

    @Override
    public void addNewWorkshop(Workshop workshop) {
        Optional<Workshop> workshopByEmail = workshopRepository.findWorkshopByEmail(workshop.getEmail());
        if (workshopByEmail.isPresent())
            throw new IllegalArgumentException("This workshop already exists");

        workshopRepository.save(workshop);
    }

    @Override
    public void deleteWorkshop(Long workshop_id) {
        boolean exists = workshopRepository.existsById(workshop_id);
        if (!exists)
            throw new IllegalStateException("Workshop with id" + workshop_id + "does not exists.");

        workshopRepository.deleteById(workshop_id);
    }

    @Override
    @Transactional
    public void updateWorkshop(Long workshopId, String name, String email, String phone, String address, String description) {
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("student with id " + workshopId + "does not exist."));

        if (name != null && name.length() > 0 && !Objects.equals(workshop.getName(), name)) {
            workshop.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(workshop.getEmail(), email)) {
            workshop.setEmail(email);
        }

        if (address != null && address.length() > 0 && !Objects.equals(workshop.getWorkshop_address(), address)) {
            workshop.setWorkshop_address(address);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(workshop.getPhone_number(), phone)) {
            workshop.setPhone_number(phone);
        }

        if (description != null && description.length() > 0 && !Objects.equals(workshop.getWorkshop_description(), description)) {
            workshop.setWorkshop_description(description);
        }

    }
}
