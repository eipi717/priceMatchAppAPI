package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.dtos.AccountCreationDTO;
import com.eipi717.pricematchapi.dtos.PasswordChangeDTO;
import com.eipi717.pricematchapi.entity.User;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "findUserByUserName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByUserName(
            @RequestParam(name = "username", required = true) String userName
    ) {
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "changePassword", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(
            @RequestBody PasswordChangeDTO passwordChangeDTO
            ) {
        SelfDefinedResponse<User> selfDefinedResponse = new SelfDefinedResponse();
        if (!userService.changePassword(passwordChangeDTO)) {
            selfDefinedResponse.setMessage("Incorrect Password!");
            return new ResponseEntity<>(selfDefinedResponse, HttpStatus.BAD_REQUEST);
        }
        selfDefinedResponse.setData(userService.getUserByUserName(passwordChangeDTO.getUsername()));
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }
    @PostMapping(value = "accountCreate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accountCreate(
            @RequestBody AccountCreationDTO accountCreationDTO
    ) {
        try {
            userService.createAccount(accountCreationDTO);
            return new ResponseEntity<>("Account created!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
