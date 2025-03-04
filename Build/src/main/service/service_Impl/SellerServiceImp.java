package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Request.BuyerUpdateDto;
import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.mapper.mappersClasses.SellerMapper;

import com.eshop.sonny.model.MySeller;
import com.eshop.sonny.repository.SellerRepository;
import com.eshop.sonny.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class SellerServiceImp implements SellerService {

    private final SellerMapper sellerMapper;
    private final SellerRepository sellerRepository;

    @Override
    public boolean saveSeller(UserRegisterDto sellerDto) {
        if(!sellerRepository.existsByBusinessName(sellerDto.getUserName())
         && !sellerRepository.existsByEmail(sellerDto.getEmail()))
            throw new RuntimeException("User already exists with this email or username");

        MySeller seller = SellerMapper.SellerRegisterDtoToSeller(sellerDto);
        sellerRepository.save(seller);
        return true;

    }

    @Override
    public boolean updateSeller(BuyerUpdateDto buyerUpdateDto) {
        MySeller seller = sellerRepository.findByBusinessName(buyerUpdateDto.getUserName());
        if(seller == null){
            throw new RuntimeException("Buyer not found");
        }

        // Update fields if they are not null
        if (buyerUpdateDto.getAddress() != null) {
            seller.setBusinessAddress(buyerUpdateDto.getAddress());
        }
        if (buyerUpdateDto.getAddressLine2() != null) {
            seller.setBusinessAddressLine2(buyerUpdateDto.getAddressLine2());
        }
        if (buyerUpdateDto.getAddressLine3() != null) {
            seller.setBusinessAddress(buyerUpdateDto.getAddressLine3());
        }
        if (buyerUpdateDto.getCity() != null) {
            seller.setBusinessCity(buyerUpdateDto.getCity());
        }
        if (buyerUpdateDto.getPostalCode() != null) {
            seller.setBusinessPostalCode(buyerUpdateDto.getPostalCode());
        }
        if (buyerUpdateDto.getPhone() != null) {
            seller.setPhone(buyerUpdateDto.getPhone());
        }
        if (buyerUpdateDto.getEmail() != null) {
            seller.setEmail(buyerUpdateDto.getEmail());
        }
        if (buyerUpdateDto.getPassword() != null) {
            seller.setPassword(buyerUpdateDto.getPassword());
        }
        if (buyerUpdateDto.getUserName() != null) {
            seller.setUserName(buyerUpdateDto.getUserName());
        }
        if(buyerUpdateDto.getBizCertNumberEIN() != null){
            seller.setBizCertNumberEIN(buyerUpdateDto.getBizCertNumberEIN());
        }
        if(buyerUpdateDto.getProfilePicture() != null){
            seller.setProfilePicture(buyerUpdateDto.getProfilePicture());
        }

        // Save updated entity
        sellerRepository.save(seller);

        return true;
    }

    @Override
    public boolean deleteSeller(String bussinessName) {
        MySeller seller = sellerRepository.findByBusinessName(bussinessName);
        if(seller != null){
            sellerRepository.delete(seller);
            return true;
        }
        throw new RuntimeException("Buyer not found");
    }

    @Override
    public boolean getSpacificSeller(String bussinessName) {
        return false;
    }
}
