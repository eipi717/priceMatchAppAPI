package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDTO {
    private String username;

    private String oldPassword;

    private String newPassword;
}
