package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.config.JWTTokenGenerator;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("*")
public class UserController {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;
    ResponseEntity responseEntity;
    @Value("${app.controller.exception.message1}")
    private String message1;

    @Value("${app.controller.exception.message2}")
    private String message2;

    @Value("${app.controller.exception.message3}")
    private String message3;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }


    @PostMapping("/register")
    public ResponseEntity saveUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
        try{
            responseEntity=new ResponseEntity(userService.saveUser(user),HttpStatus.CREATED);
        }catch(UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

    @PutMapping("/update/{email}")
    public ResponseEntity updateRegistration(@RequestBody User user){
        User userSaved = userService.update(user);
        return new ResponseEntity(userSaved, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null){
                throw new UserNotFoundException(message1);
        }
        User userDetails = userService.login(user);
        if (userDetails == null) {
            throw new UserNotFoundException(message2);
        }
        if (!(user.getPassword().equals(userDetails.getPassword()))) {
            throw new UserNotFoundException(message3);
        }
        responseEntity = new ResponseEntity<>(jwtTokenGenerator.generateToken(userDetails), HttpStatus.OK);
    } catch(UserNotFoundException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/allUsers")
    public List<User> allUser() {
        return userService.allUsers();
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) throws UserNotFoundException{
        try {
            responseEntity = new ResponseEntity(userService.loadUserByUsername(email), HttpStatus.OK);
        }catch (UserNotFoundException e){
            e.printStackTrace();
        }
        return responseEntity;
    }


    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUserById(@PathVariable("email") String email) throws UserNotFoundException{
        userService.deleteUserById(email);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
