package com.eshop.sonny.dto.Request;

import com.eshop.sonny.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    private String userName;
    private String password;
    private String email;
    private String phone;
    private Role role;

}
