package com.eshop.sonny.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationRequestMethodsDto {
  private String card_type;
  private String card_number;
//  private String security_code;
  private Boolean is_default_payment_method;
  private Boolean is_billing_same_as_delivery_address;
  private Boolean is_billing_same_as_user_address;
  // are this billing address will be unchangeable for each payment methods?
  private String billing_address;
  private String billing_address_line_2;
  private String billing_address_line_3;
  private String billing_city;
  private String billing_postal_code;

}