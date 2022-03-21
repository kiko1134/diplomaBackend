package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.DTO.UserDto;
import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.model.Workshop;
import com.example.diplomaupdated.repo.*;
import com.example.diplomaupdated.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepo accountRepo;
    private final ServiceRepo serviceRepo;
    private final RoleRepo roleRepo;
    private final WorkshopRepo workshopRepo;
    private final WorkshopServiceRepo workshopServiceRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void addNewUser(UserDto userDto) {

        Optional<Account> accountByName = accountRepo.findAccountByName(userDto.getName());
        Optional<Account> accountByEmail = accountRepo.findAccountByEmail(userDto.getEmail());

        if (accountByName.isPresent() || accountByEmail.isPresent())
            throw new IllegalArgumentException("Email or username already in use");

        Account currAccount = new Account();

        currAccount.setName(userDto.getName());
        currAccount.setEmail(userDto.getEmail());
        currAccount.setPassword(passwordEncoder.encode(userDto.getPassword()));
        currAccount.setRole(roleRepo.findRoleByName(userDto.getRole()));

        accountRepo.save(currAccount);

        User currentUser = new User();

        currentUser.setAccount(currAccount);
        userRepo.save(currentUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));
        accountRepo.deleteById(user.getAccount().getId());
        userRepo.deleteById(userId);
    }

    @Override
    public void updateUser(Long userId,String email) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));

        if (email != null && email.length() > 0 && !Objects.equals(user.getAccount().getEmail(), email)) {
            Optional<Account> currentUserAccount = accountRepo.findAccountByEmail(email);
            if(currentUserAccount.isPresent())
                throw new IllegalStateException("Email taken");
            user.getAccount().setEmail(email);
        }
    }

    @Override
    public void addFavoriteService(Long serviceId, Long workshopId, Long userId) {

        Workshop workshop = workshopRepo.findById(workshopId)
                .orElseThrow(() -> new IllegalStateException("Workshop with id" + workshopId + "does not exist"));

        com.example.diplomaupdated.model.Service service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new IllegalStateException("Service with id" + serviceId + "does not exist"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));

        List<com.example.diplomaupdated.model.WorkshopService> all = workshopServiceRepo.findAll();

        for (com.example.diplomaupdated.model.WorkshopService obj: all){
            if(obj.getService().getId().equals(serviceId) && obj.getWorkshop().getId().equals(workshopId)){
                Set<com.example.diplomaupdated.model.WorkshopService> favoriteServices = user.getFavoriteServices();
                favoriteServices.add(obj);
                user.setFavoriteServices(favoriteServices);
            }

        }


    }
}
