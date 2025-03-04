package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Request.BuyerUpdateDto;
import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.mapper.mappersClasses.BuyerMapper;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.repository.BuyerRepository;
import com.eshop.sonny.service.BuyerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class BuyerServiceImp implements BuyerService {

    BuyerMapper buyerMapper;
    BuyerRepository buyerRepository;
    @Override
    public boolean saveBuyer(UserRegisterDto buyerDto) {
        // check if there is a user with the same username and email

        Buyer buyerByUserName = buyerRepository.findByUserName(buyerDto.getUserName());
        Buyer buyerByEmail = buyerRepository.findByEmail(buyerDto.getEmail());

        if (buyerByUserName == null && buyerByEmail == null) {
            Buyer buyer = BuyerMapper.BuyerRegisterDtoToBuyer(buyerDto);
            buyerRepository.save(buyer);
            return true;
        }else{
            throw new RuntimeException("User already exists with this email or username");
        }
    }

    @Override
    public boolean updateBuyer(String userName, BuyerUpdateDto buyerUpdateDto) {
        Buyer buyer = buyerRepository.findByUserName(userName);
        if(buyer == null){
            throw new RuntimeException("Buyer not found");
        }

        // Update fields if they are not null
        if (buyerUpdateDto.getAddress() != null) {
            buyer.setAddress(buyerUpdateDto.getAddress());
        }
        if (buyerUpdateDto.getAddressLine2() != null) {
            buyer.setAddressLine2(buyerUpdateDto.getAddressLine2());
        }
        if (buyerUpdateDto.getAddressLine3() != null) {
            buyer.setAddressLine3(buyerUpdateDto.getAddressLine3());
        }
        if (buyerUpdateDto.getCity() != null) {
            buyer.setCity(buyerUpdateDto.getCity());
        }
        if (buyerUpdateDto.getPostalCode() != null) {
            buyer.setPostalCode(buyerUpdateDto.getPostalCode());
        }
        if (buyerUpdateDto.getPhone() != null) {
            buyer.setPhone(buyerUpdateDto.getPhone());
        }
        if (buyerUpdateDto.getEmail() != null) {
            buyer.setEmail(buyerUpdateDto.getEmail());
        }
        if (buyerUpdateDto.getPassword() != null) {
            buyer.setPassword(buyerUpdateDto.getPassword());
        }
        if (buyerUpdateDto.getUserName() != null) {
            buyer.setUserName(buyerUpdateDto.getUserName());
        }
        if(buyerUpdateDto.getProfilePicture() != null){
            buyer.setProfilePicture(buyerUpdateDto.getProfilePicture());
        }

        // Save updated entity
        buyerRepository.save(buyer);

        return true;
    }

    @Override
    public boolean deleteBuyer(String userName) {
        Buyer buyer = buyerRepository.findByUserName(userName);
        if(buyer != null){
            buyerRepository.delete(buyer);
            return true;
        }
        throw new RuntimeException("Buyer not found");
    }

    // i need to know what should i send when the user want to see his profile
    @Override
    public BuyerUpdateDto getBuyer(String userName) {
        return null;
    }

}
