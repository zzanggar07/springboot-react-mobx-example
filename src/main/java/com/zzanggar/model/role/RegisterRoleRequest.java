package com.zzanggar.model.role;

import com.zzanggar.constants.RoleType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "권한 저장", description = "권한 저장 요청")
public class RegisterRoleRequest {
    @NotNull(message = "error.empty.role")
    @Getter @Setter
    private RoleType roleType;
}
