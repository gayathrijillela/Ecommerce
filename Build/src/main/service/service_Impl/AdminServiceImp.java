package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.dto.Response.AdminDetailsResponseDto;
import com.eshop.sonny.dto.Response.AdminDto;
import com.eshop.sonny.mapper.mappersClasses.AdminMapper;
import com.eshop.sonny.model.Admin;
import com.eshop.sonny.model.enums.Role;
import com.eshop.sonny.repository.AdminRepository;
import com.eshop.sonny.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@AllArgsConstructor
public class AdminServiceImp implements AdminService {

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;

    @Override
    public boolean addAdmin(UserRegisterDto userRegisterDto) {
        if(adminRepository.existsByUserName(userRegisterDto.getUserName()))
            throw new RuntimeException("Admin already exists");
        Admin admin = new Admin();
        admin.setUserName(userRegisterDto.getUserName());
        admin.setRole(userRegisterDto.getRole());
        admin.setCreated_at(LocalDateTime.now());
        admin.setEmail(userRegisterDto.getEmail());
        admin.setPassword(userRegisterDto.getPassword());
        admin.setPhone(userRegisterDto.getPhone());
        adminRepository.save(admin);
        return true;
    }

    @Override
    public boolean removeAdmin(String userName) {
        if(adminRepository.existsByUserName(userName)) {
            adminRepository.deleteByUserName(userName);
            return true;
        }
        throw new RuntimeException("Admin not found");
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDto> adminDtos = new ArrayList<>()   ;
        for(Admin admin : admins) {
            AdminDto adminDto = AdminMapper.AdminToAdminResponseDto(admin);
            adminDtos.add(adminDto);
        }
        return adminDtos;
    }

    @Override
    public AdminDto getAdmin(String userName) {
        // do we want details?
        return AdminMapper.AdminToAdminResponseDto(adminRepository.findByUserName(userName).orElseThrow(
                () -> new RuntimeException("admin with this user name not found")
        ));
    }

    @Override
    public AdminDetailsResponseDto getAdminDetails(String userName) {
        Admin admin = adminRepository.findByUserName(userName).orElseThrow(
                () -> new RuntimeException("admin with this user name not found")
        );
        AdminDetailsResponseDto adminDetailsResponseDto = new AdminDetailsResponseDto();
        adminDetailsResponseDto.setUserName(admin.getUsername());
        adminDetailsResponseDto.setEmail(admin.getEmail());
        adminDetailsResponseDto.setPhoneNumber(admin.getPhone());
        return adminDetailsResponseDto;
    }


}
