package com.zzanggar.model.customer;

import com.zzanggar.constants.Length;
import com.zzanggar.validators.UniqueCustomerEmail;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "고객등록 요청", description = "고객등록 요청")
public class RegisterCustomerRequest {
    @Size(max = Length.BUSINESS_CATEGORY, message = "error.size.limit.businessCategory")
    private String businessCategory;

    @NotNull(message = "error.empty.companyName")
    @Size(max = Length.COMPANY_NAME, message = "error.size.limit.companyName")
    private String companyName;

    @Size(max = Length.LICENSE_NUMBER, message = "error.size.limit.licenseNumber")
    private String licenseNumber;

    @NotNull(message = "error.empty.companyAddress")
    @Size(max = Length.COMPANY_ADDRESS, message = "error.size.limit.companyAddress")
    private String companyAddress;

    @NotNull(message = "error.empty.managerName")
    @Size(max = Length.MANAGER_NAME, message = "error.size.limit.managerName")
    private String managerName;

    @Size(max = Length.POSITION, message = "error.size.limit.position")
    private String position;

    @Size(max = Length.DEPARTMENT, message = "error.size.limit.department")
    private String department;

    @NotNull(message = "error.empty.telNumber")
    @Size(max = Length.TEL_NUMBER, message = "error.size.limit.telNumber")
    private String telNumber;

    @Size(max = Length.PHONE_NUMBER, message = "error.size.limit.phoneNumber")
    private String phoneNumber;

    @Size(max = Length.FAX_NUMBER, message = "error.size.limit.faxNumber")
    private String faxNumber;

    @NotNull(message = "error.empty.email")
    @Email(message = "error.valid.email")
    @UniqueCustomerEmail(message = "error.exists.email")
    @Size(max = Length.EMAIL, message = "error.size.limit.email")
    private String email;

    @NotNull(message = "error.empty.finalCreator")
    @Size(max = Length.FINAL_CREATOR, message = "error.size.limit.finalCreator")
    private String finalCreator;

    @Size(max = Length.MEMO, message = "error.size.limit.memo")
    private String memo;
}
