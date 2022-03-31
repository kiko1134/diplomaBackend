package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.DTO.WorkshopDto;
import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.model.Service;
import com.example.diplomaupdated.model.Workshop;
import com.example.diplomaupdated.repo.*;
import com.example.diplomaupdated.service.WorkshopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepo workshopRepo;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepo accountRepo;
    private final ServiceRepo serviceRepo;
    private final WorkshopServiceRepo workshopServiceRepo;
    private final RoleRepo roleRepo;

    @Override
    public List<Workshop> getWorkshops() {
        return workshopRepo.findAll();
    }

    @Override
    public List<com.example.diplomaupdated.model.WorkshopService> getWorkshopServices() {
        return workshopServiceRepo.findAll();
    }


    @Override
    public void registerNewWorkshop(WorkshopDto workshopDto) {
        Optional<Account> accountByName = accountRepo.findAccountByName(workshopDto.getName());
        Optional<Account> accountByEmail = accountRepo.findAccountByEmail(workshopDto.getEmail());

        if (accountByName.isPresent() || accountByEmail.isPresent())
            throw new IllegalArgumentException("Email or username already in use");

        Account currAccount = new Account();
        currAccount.setName(workshopDto.getName());
        currAccount.setEmail(workshopDto.getEmail());
        currAccount.setPassword(passwordEncoder.encode(workshopDto.getPassword()));
        currAccount.setRole(roleRepo.findRoleByName(workshopDto.getRole()));

        accountRepo.save(currAccount);

        Workshop currWorkshop = new Workshop();

        currWorkshop.setAccount(currAccount);
        currWorkshop.setPhone_number(workshopDto.getPhone_number());
        currWorkshop.setWorkshop_address(workshopDto.getWorkshop_address());
        currWorkshop.setWorkshop_description(workshopDto.getWorkshop_description());

        workshopRepo.save(currWorkshop);
    }

    @Override
    public void deleteWorkshop(Long workshopId) {
        Workshop workshop = workshopRepo.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + workshopId + "does not exist"));

        accountRepo.deleteById(workshop.getAccount().getId());
        workshopRepo.deleteById(workshopId);
    }

    @Override
    public void updateWorkshop(Long id, String name, String email, String workshop_address, String phone_number, String workshop_description) {
        Workshop workshop = workshopRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + id + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(workshop.getAccount().getName(), name))
            workshop.getAccount().setName(name);

        if (email != null && email.length() > 0 && !Objects.equals(workshop.getAccount().getEmail(), email)) {
            Optional<Account> currentUserAccount = accountRepo.findAccountByEmail(email);
            if(currentUserAccount.isPresent())
                throw new IllegalStateException("Email taken");
            workshop.getAccount().setEmail(email);
        }

        if (workshop_address != null && workshop_address.length() > 0 && !Objects.equals(workshop.getWorkshop_address(), workshop_address))
            workshop.setWorkshop_address(workshop_address);

        if (phone_number != null && phone_number.length() > 0 && !Objects.equals(workshop.getPhone_number(), phone_number))
            workshop.setPhone_number(phone_number);

        if (workshop_description != null && workshop_description.length() > 0 && !Objects.equals(workshop.getWorkshop_description(), workshop_description))
            workshop.setWorkshop_description(workshop_description);
    }

    @Override
    public void addServiceToWorkshop(Long workshopId, String serviceName, Double price) {
        Workshop workshop = workshopRepo.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + workshopId + "does not exist"));

        com.example.diplomaupdated.model.Service service = serviceRepo.findByName(serviceName)
                .orElseThrow(() -> new IllegalStateException("Service " + serviceName + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        for (com.example.diplomaupdated.model.WorkshopService obj: all){
            if(obj.getService().getName().equals(serviceName) && obj.getWorkshop().getId().equals(workshopId))
                throw new IllegalArgumentException("This service was previously added");
        }

        com.example.diplomaupdated.model.WorkshopService workshopService = new com.example.diplomaupdated.model.WorkshopService();

        workshopService.setWorkshop(workshop);
        workshopService.setService(service);
        workshopService.setPrice(price);

        workshopServiceRepo.save(workshopService);
    }

    @Override
    public void deleteServiceFromWorkshop(Long workshopId, String serviceName, Double price) {

        Workshop workshop = workshopRepo.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + workshopId + "does not exist"));

        com.example.diplomaupdated.model.Service service = serviceRepo.findByName(serviceName)
                .orElseThrow(() -> new IllegalStateException("Service " + serviceName + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        Long id = null;

        for (com.example.diplomaupdated.model.WorkshopService obj: all){
            if(obj.getService().getName().equals(serviceName) && obj.getWorkshop().getId().equals(workshopId))
                id = obj.getId();
        }
        workshopServiceRepo.deleteById(id);
//        com.example.diplomaupdated.model.WorkshopService workshopService = new com.example.diplomaupdated.model.WorkshopService();
//
//        workshopService.setWorkshop(workshop);
//        workshopService.setService(service);
//        workshopService.setPrice(price);
//
//        workshopServiceRepo.delete(workshopService);
    }

    @Override
    public void updateServiceToWorkshop(Long workshopId, String serviceName, Double price) {
        Workshop workshop = workshopRepo.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + workshopId + "does not exist"));

        com.example.diplomaupdated.model.Service service = serviceRepo.findByName(serviceName)
                .orElseThrow(() -> new IllegalStateException("Service " + serviceName + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        for (com.example.diplomaupdated.model.WorkshopService obj: all){
            if(obj.getService().getName().equals(serviceName) && obj.getWorkshop().getId().equals(workshopId))
                obj.setPrice(price);
        }
    }


    @Override
    public List<com.example.diplomaupdated.model.WorkshopService> getWorkshopServicesServiceId(String serviceId) {
        Service service = serviceRepo.findById(Long.parseLong(serviceId))
                .orElseThrow(() -> new IllegalStateException("Service with id: " + serviceId + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> resultArr = new ArrayList<>();

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        for(com.example.diplomaupdated.model.WorkshopService workshopService: all){
            if(workshopService.getService().getId().equals(Long.parseLong(serviceId)))
                resultArr.add(workshopService);
        }

        return resultArr;
    }

    @Override
    public List<com.example.diplomaupdated.model.WorkshopService> getWorkshopServicesWorkshopName(String workshopName) {
//        Workshop workshop = workshopRepo.findWorkshopBy(workshopName)
//                .orElseThrow(() -> new IllegalStateException("Workshop " + workshopName + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> resultArr = new ArrayList<>();

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        for(com.example.diplomaupdated.model.WorkshopService obj: all){
            if(obj.getWorkshop().getAccount().getName().equals(workshopName))
                resultArr.add(obj);
        }

        return resultArr;
    }


}
