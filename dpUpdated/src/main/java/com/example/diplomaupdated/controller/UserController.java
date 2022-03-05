package com.example.diplomaupdated.controller;


import com.example.diplomaupdated.DTO.userDto;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/users")
public class UserController {

    private UserServiceImpl userService;

    @GetMapping
    public List<User> getUsers(){return userService.getUsers();}

    @CrossOrigin(origins = "http://localhost:3000/register-customer")
    @PostMapping
    public void registerNewUser(@RequestBody userDto userDto){
        userService.addNewUser(userDto);
    }

    @DeleteMapping(path = "{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id){userService.deleteUser(user_id);}

    @PutMapping(path = "{user_id}")
    public void updateUser(@PathVariable("user_id") Long user_id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password){
        userService.updateUser(user_id,name,email,password);
    }
}
