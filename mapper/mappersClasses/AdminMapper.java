package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Response.AdminDto;
import com.eshop.sonny.model.Admin;
import org.springframework.stereotype.Component;

@Component

public class  AdminMapper {

    public static AdminDto AdminToAdminResponseDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setUserName(admin.getUsername());
        return adminDto;
    }

}
