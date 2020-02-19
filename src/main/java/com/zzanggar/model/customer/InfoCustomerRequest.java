package com.zzanggar.model.customer;

import com.zzanggar.constants.Length;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Data
public class InfoCustomerRequest {

    @Size(max = Length.LICENSE_NUMBER, message = "error.size.limit.licenseNumber")
    private String licenseNumber;

    @Email(message = "error.valid.email")
    @Size(max = Length.EMAIL, message = "error.size.limit.email")
    private String email;
}
