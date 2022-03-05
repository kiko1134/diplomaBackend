package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.DTO.userDto;
import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.repo.accountRepo;
import com.example.diplomaupdated.repo.userRepo;
import com.example.diplomaupdated.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final userRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private final accountRepo accountRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepo.findAccountByName(username)
                .orElseThrow(() -> new IllegalStateException("user not found"));


        User user = userRepo.findUserByAccountId(account.getId());

        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(user.getAccount().getRole().getName()));

        return new org.springframework.security.core.userdetails.User(user.getAccount().getName(),
                user.getAccount().getPassword(), simpleGrantedAuthorities);
    }


    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void addNewUser(userDto userDto) {

        Optional<Account> accountByName = accountRepo.findAccountByName(userDto.getUsername());
        Optional<Account> accountByEmail = accountRepo.findAccountByEmail(userDto.getEmail());

        if (accountByName.isPresent() || accountByEmail.isPresent())
            throw new IllegalArgumentException("Email or username already in use");

        Account currAccount = new Account();

        currAccount.setName(userDto.getUsername());
        currAccount.setEmail(userDto.getEmail());
        currAccount.setPassword(passwordEncoder.encode(userDto.getPassword()));

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
}
