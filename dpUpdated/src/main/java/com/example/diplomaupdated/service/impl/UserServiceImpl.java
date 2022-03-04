//package com.example.diplomaupdated.service.impl;
//
//import com.example.diplomaupdated.model.User;
//import com.example.diplomaupdated.repo.userRepo;
//import com.example.diplomaupdated.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class UserServiceImpl implements UserService, UserDetailsService {
//
//    private final userRepo userRepo;
//    private PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findUserByName(username);
//        if (user.isEmpty())
//            throw new UsernameNotFoundException("User not found");
//
//        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(user.get().getRegister().getRole().getName()));
//
//        return new org.springframework.security.core.userdetails.User(user.get().getRegister().getName(),
//                user.get().getRegister().getPassword(), simpleGrantedAuthorities);
//    }
//
//
//    @Override
//    public List<User> getUsers(User user) {
//        return userRepo.findAll();
//    }
//
//    @Override
//    public void addNewUser(User user) {
//        Optional<User> userByEmail = userRepo.findUserByEmail(user.getRegister().getEmail());
//        Optional<User> userByUsername = userRepo.findUserByName(user.getRegister().getRole().getName());
//
//        if (userByEmail.isPresent() || userByUsername.isPresent()) {
//            throw new IllegalArgumentException("Email or username is already in use");
//        }
//
//        user.getRegister().setPassword(passwordEncoder.encode(user.getRegister().getPassword()));
//
//        userRepo.save(user);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        boolean exists = userRepo.existsById(userId);
//        if (!exists) {
//            throw new IllegalStateException("User with id" + userId + "does not exist.");
//        }
//        userRepo.deleteById(userId);
//    }
//
//    @Override
//    public void updateUser(Long userId, String name, String email, String password) {
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exists"));
//
//        if (name != null && name.length() > 0 && !Objects.equals(user.getRegister().getName(), name))
//            user.getRegister().setName(name);
//
//        if (email != null && email.length() > 0 && !Objects.equals(user.getRegister().getEmail(), email)) {
//            Optional<User> exists = userRepo.findUserByEmail(email);
//            if (exists.isPresent())
//                throw new IllegalStateException("Email taken");
//            user.getRegister().setEmail(email);
//        }
//
//        if (password != null && password.length() > 0 && !Objects.equals(user.getRegister().getPassword(), password)) {
//            user.getRegister().setPassword(password);
//        }
//    }
//}
