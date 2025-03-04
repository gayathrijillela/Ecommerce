package com.eshop.sonny.service;

import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.dto.Response.AdminDetailsResponseDto;
import com.eshop.sonny.dto.Response.AdminDto;
import com.eshop.sonny.model.Admin;

import java.util.List;

public interface AdminService {

    boolean addAdmin(UserRegisterDto adminDto);

    boolean removeAdmin(String userName);

    List<AdminDto> getAllAdmins();

    AdminDto getAdmin(String userName);

    AdminDetailsResponseDto getAdminDetails(String userName);

}
