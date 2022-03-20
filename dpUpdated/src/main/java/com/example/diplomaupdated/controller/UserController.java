package com.example.diplomaupdated.controller;


import com.example.diplomaupdated.DTO.RequestCustomerDto;
import com.example.diplomaupdated.DTO.UserDto;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.service.RoleService;
import com.example.diplomaupdated.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/users")
public class UserController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getUsers(){return userService.getUsers();}

//    @GetMapping
//    public User getUserById(Long id){return new User();}



    @DeleteMapping(path = "{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id){userService.deleteUser(user_id);}

    @PutMapping(path = "{user_id}")
    public void updateUser(@PathVariable("user_id") Long user_id, @RequestBody RequestCustomerDto user){
        userService.updateUser(user_id,user.getEmail());
    }

    @PostMapping(path = "role/{role}")
    public void addRole(@PathVariable String role){
        roleService.addNewRole(role);
    }

    @PostMapping(path = "role/delete/{role}")
    public void deleteRole(@PathVariable String role){
        roleService.deleteRole(role);
    }
}
