package com.eshop.sonny.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SubCategoryRequestDto {
    private String subCategoryName;
    private String subCategoryUrl;
    private String subCategoryBanner;
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
