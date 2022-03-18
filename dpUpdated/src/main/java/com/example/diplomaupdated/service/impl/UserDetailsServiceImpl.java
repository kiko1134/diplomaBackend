package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.repo.AccountRepo;
import com.example.diplomaupdated.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepo.findAccountByName(username)
                .orElseThrow(() -> new IllegalStateException("user not found"));


        return account;
    }
}
