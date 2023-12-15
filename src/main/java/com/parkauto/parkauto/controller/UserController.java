package com.parkauto.parkauto.controller;

import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUser() {
       List<User> user = userService.getUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(
            @RequestParam("firstname") String firstname,
            @RequestParam("laststname") String lastname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("role") String role,
            @RequestParam("active") String active,
            @RequestParam("isNotLocked") String isNotLocked,
            @RequestParam(value = "profileImage", required = false)MultipartFile profileImage
            ){
        User newUser = userService.addNewUser(firstname,lastname,username,password,email,role,Boolean.parseBoolean(active),Boolean.parseBoolean(isNotLocked),profileImage);
        return new ResponseEntity<>(newUser,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        //return response(HttpResponse.OK,"User is deleted successful"+id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}