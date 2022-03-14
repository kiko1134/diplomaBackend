package com.example.diplomaupdated.controller;


import com.example.diplomaupdated.DTO.AuthenticationDto;
import com.example.diplomaupdated.DTO.JwtResponse;
import com.example.diplomaupdated.repo.RoleRepo;
import com.example.diplomaupdated.repo.UserRepo;
import com.example.diplomaupdated.security.jwt.JwtUtils;
import com.example.diplomaupdated.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "v1/signin")
public class SignIn {

    private final AuthenticationManager AuthenticationManager;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationDto loginRequest){
        Authentication authentication = AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getEmail(),userDetails.getAuthorities()));
    }

}
