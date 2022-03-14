package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.DTO.UserDto;
import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.repo.AccountRepo;
import com.example.diplomaupdated.repo.RoleRepo;
import com.example.diplomaupdated.repo.ServiceRepo;
import com.example.diplomaupdated.repo.UserRepo;
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
    public void updateUser(Long userId, String name, String email, String password) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(user.getAccount().getName(), name))
            user.getAccount().setName(name);

        if (email != null && email.length() > 0 && !Objects.equals(user.getAccount().getEmail(), email)) {
            Account currentUserAccount = accountRepo.findAccountByEmail(email)
                    .orElseThrow(() -> new IllegalStateException("Email taken"));
            user.getAccount().setEmail(email);
        }

        if (password != null && password.length() > 0 && !Objects.equals(user.getAccount().getPassword(), password)) {
            user.getAccount().setPassword(password);
        }
    }

    @Override
    public void addFavoriteService(Long serviceId, Long userId) {
        com.example.diplomaupdated.model.Service service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new IllegalStateException("Service with id" + serviceId + "does not exist"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));

        Set<com.example.diplomaupdated.model.Service> favoriteServices = user.getFavoriteServices();
        favoriteServices.add(service);
        user.setFavoriteServices(favoriteServices);
    }
}
