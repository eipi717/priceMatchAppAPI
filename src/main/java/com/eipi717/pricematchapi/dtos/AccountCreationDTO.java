package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationDTO {
    private String username;
    private String password;
    private String email;
}
