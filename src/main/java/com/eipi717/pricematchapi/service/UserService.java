package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtos.AccountCreationDTO;
import com.eipi717.pricematchapi.dtos.PasswordChangeDTO;
import com.eipi717.pricematchapi.entity.User;
import com.eipi717.pricematchapi.repository.UserRepository;
import com.eipi717.pricematchapi.utils.HashUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean changePassword(PasswordChangeDTO passwordChangeDTO) {
        User user = getUserByUserName(passwordChangeDTO.getUsername());
        if (!HashUtils.sha256(passwordChangeDTO.getOldPassword()).equals(user.getPassword())) return false;
        user.setPassword(HashUtils.sha256(passwordChangeDTO.getNewPassword()));

        userRepository.save(user);
        return true;
    }

    public void createAccount(AccountCreationDTO accountCreationDTO) {
        User user = new User();
        user.setUserName(accountCreationDTO.getUsername());
        user.setPassword(HashUtils.sha256(accountCreationDTO.getPassword()));
        user.setEmail(accountCreationDTO.getEmail());

        userRepository.save(user);
    }
}
