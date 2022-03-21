package com.example.diplomaupdated.controller;


import com.example.diplomaupdated.DTO.*;
import com.example.diplomaupdated.model.Account;
import com.example.diplomaupdated.repo.RoleRepo;
import com.example.diplomaupdated.repo.UserRepo;
import com.example.diplomaupdated.security.jwt.JwtUtils;
import com.example.diplomaupdated.service.RoleService;
import com.example.diplomaupdated.service.UserService;
import com.example.diplomaupdated.service.WorkshopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "v1/public")
public class AuthenticateController {

    private final AuthenticationManager AuthenticationManager;
    private final UserRepo userRepo;
    private final UserService userService;
    private final RoleRepo roleRepo;
    private final JwtUtils jwtUtils;
    private final WorkshopService workshopService;
    private final RoleService roleService;

    @PostMapping(path = "/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationDto loginRequest){
        Authentication authentication = AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Account userDetails = (Account) authentication.getPrincipal();
        if(userDetails.getRole().getName().equals("USER"))
            return ResponseEntity.ok(new UserResponseDto(userDetails.getUser().getId(),jwt,
                    userDetails.getUsername(), userDetails.getEmail(),userDetails.getAuthorities()));

        return ResponseEntity.ok(new WorkshopResponseDto(userDetails.getWorkshop().getId(),jwt,userDetails.getUsername(),userDetails.getEmail(),
                userDetails.getWorkshop().getPhone_number(),userDetails.getWorkshop().getWorkshop_address(),
                userDetails.getWorkshop().getWorkshop_description(),userDetails.getAuthorities()));
    }

    @PostMapping(path = "/register-user")
    public void registerNewUser(@RequestBody UserDto userDto){
        userService.addNewUser(userDto);
    }

    @PostMapping(path = "/register-workshop")
    public void registerWorkshop(@RequestBody WorkshopDto workshopDto){
        workshopService.registerNewWorkshop(workshopDto);
    }

    @PostMapping("add-role/{role}")
    public void addRole(@PathVariable String role){
        roleService.addNewRole(role);
    }






}
