package com.example.rest.rest.controller;


import com.example.rest.rest.model.Dto.UserDto;
import com.example.rest.rest.model.User;
import com.example.rest.rest.model.UserForm;
import com.example.rest.rest.model.form.UserUpdateForm;
import com.example.rest.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    private UserService userService;

    @GetMapping
    public List<UserDto> list(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id){

        return userService.findUserById(id);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid UserForm userForm){
        return userService.createUser(userForm);
    }

    @PutMapping("/{id}")
    public UserDto updateById(@RequestBody UserUpdateForm form, @PathVariable("id") Long id){
        return userService.updateUserById(form, id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userService.deletById(id);
    }




}
