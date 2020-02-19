package com.zzanggar.model.customer;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "복수고객등록 요청", description = "복수고객등록 요청")
public class RegistersCustomerRequest {
    private List<RegisterCustomerRequest> requests;
}
