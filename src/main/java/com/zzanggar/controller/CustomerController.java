package com.zzanggar.controller;

import com.zzanggar.constants.ParamName;
import com.zzanggar.constants.ParamTitle;
import com.zzanggar.constants.SearchRule;
import com.zzanggar.constants.ServerError;
import com.zzanggar.entity.Customer;
import com.zzanggar.exception.FieldException;
import com.zzanggar.exception.ServiceException;
import com.zzanggar.model.customer.*;
import com.zzanggar.response.ListResponse;
import com.zzanggar.response.ObjectResponse;
import com.zzanggar.service.CustomerService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = {"api/customer/v1", "ajax/customer/v1"})
@Api(value = "고객 관리", description = "고객 관리 API")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping(value = "/register")
    @ApiOperation(
            value = "고객 등록",
            notes = "고객 등록 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ParamName.BUSINESS_CATEGORY, value = ParamTitle.BUSINESS_CATEGORY, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.COMPANY_NAME, value = ParamTitle.COMPANY_NAME, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.LICENSE_NUMBER, value = ParamTitle.LICENSE_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.COMPANY_ADDRESS, value = ParamTitle.COMPANY_ADDRESS, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.MANAGER_NAME, value = ParamTitle.MANAGER_NAME, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.POSITION, value = ParamTitle.POSITION, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.DEPARTMENT, value = ParamTitle.DEPARTMENT, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.TEL_NUMBER, value = ParamTitle.TEL_NUMBER, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.PHONE_NUMBER, value = ParamTitle.PHONE_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.FAX_NUMBER, value = ParamTitle.FAX_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.EMAIL, value = ParamTitle.EMAIL, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.FINAL_CREATOR, value = ParamTitle.FINAL_CREATOR, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.MEMO, value = ParamTitle.MEMO, dataType = "string", paramType = "query")
    })
    public ObjectResponse<Customer> register(@ApiParam(hidden = true) @Valid RegisterCustomerRequest registerCustomerRequest, BindingResult result) throws ServiceException {
        if (result.hasErrors()) {
            throw new FieldException(result);
        }
        return new ObjectResponse<>(customerService.register(registerCustomerRequest));
    }

    @Transactional
    @PostMapping(value = "/registers")
    @ApiOperation(
            value = "복수고객 등록",
            notes = "복수고객 등록 API")
    public ObjectResponse<List<Customer>> registers(@ApiParam(value = ParamTitle.CUSTOMERS, required = true, allowMultiple = true) @RequestBody RegistersCustomerRequest registersCustomerRequest) throws ServiceException {
        List<Customer> customers = new ArrayList<>();

        for (RegisterCustomerRequest registerCustomerRequest : registersCustomerRequest.getRequests()) {
            customers.add(customerService.register(registerCustomerRequest));
        }

        return new ObjectResponse<>(customers);
    }

    @PutMapping(value = "/edit/{customerIdx}")
    @ApiOperation(
            value = "고객 정보 수정",
            notes = "고객 정보 수정 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ParamName.BUSINESS_CATEGORY, value = ParamTitle.BUSINESS_CATEGORY, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.COMPANY_NAME, value = ParamTitle.COMPANY_NAME, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.LICENSE_NUMBER, value = ParamTitle.LICENSE_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.COMPANY_ADDRESS, value = ParamTitle.COMPANY_ADDRESS, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.MANAGER_NAME, value = ParamTitle.MANAGER_NAME, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.POSITION, value = ParamTitle.POSITION, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.DEPARTMENT, value = ParamTitle.DEPARTMENT, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.TEL_NUMBER, value = ParamTitle.TEL_NUMBER, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.PHONE_NUMBER, value = ParamTitle.PHONE_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.FAX_NUMBER, value = ParamTitle.FAX_NUMBER, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.EMAIL, value = ParamTitle.EMAIL, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.FINAL_CREATOR, value = ParamTitle.FINAL_CREATOR, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.MEMO, value = ParamTitle.MEMO, dataType = "string", paramType = "query")
    })
    public ObjectResponse<Customer> edit(@ApiParam(value = ParamTitle.CUSTOMER_IDX, required = true) @PathVariable Long customerIdx,
                                         @ApiParam(hidden = true) @Valid EditCustomerRequest editCustomerRequest, BindingResult result) throws ServiceException {
        if (result.hasErrors()) {
            throw new FieldException(result);
        }
        return new ObjectResponse<>(customerService.edit(customerIdx, editCustomerRequest));
    }

    @DeleteMapping(value = "/delete/{customerIdx}")
    @ApiOperation(
            value = "고객 삭제",
            notes = "고객 삭제 API")
    public ObjectResponse<Customer> delete(@ApiParam(value = ParamTitle.CUSTOMER_IDX, required = true) @PathVariable Long customerIdx) throws ServiceException {
        return new ObjectResponse<>(customerService.delete(customerIdx));
    }

    @PostMapping(value = "/delete")
    @ApiOperation(
            value = "복수고객 삭제",
            notes = "복수고객 삭제 API")
    public ObjectResponse<List<Customer>> delete(@ApiParam(value = ParamTitle.CUSTOMER_IDXES, required = true, allowMultiple = true) @RequestParam List<Long> customerIdxes) throws ServiceException {
        return new ObjectResponse<>(customerService.delete(customerIdxes));
    }

    @GetMapping(value = "/list")
    @ApiOperation(
            value = "고객 목록",
            notes = "고객 목록 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ParamName.SEARCH_RULE, value = ParamTitle.SEARCH_RULE, dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = ParamName.KEYWORD, value = ParamTitle.KEYWORD, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = ParamName.PAGE, value = ParamTitle.PAGE, dataType = "integer", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = ParamName.SIZE, value = ParamTitle.SIZE, dataType = "integer", paramType = "query", defaultValue = "20"),
            @ApiImplicitParam(name = ParamName.SORT, value = ParamTitle.SORT, dataType = "string", paramType = "query", allowMultiple = true)
    })
    public ListResponse listAll(SearchRule searchRule, String keyword,
                                @ApiParam(hidden = true) @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = Integer.SIZE) Pageable pageable) {
        return customerService.list(searchRule, keyword, pageable);
    }

    @GetMapping(value = "/group/all")
    @ApiOperation(
            value = "그룹 목록",
            notes = "그룹 목록 API")
    public ObjectResponse<GroupResponse> groupCustomer() throws ServiceException {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setBusinessCategory(customerService.getBusinessCategoryList());
        groupResponse.setPosition(customerService.getPositionList());
        groupResponse.setDepartment(customerService.getDepartmentList());
        groupResponse.setFinalCreator(customerService.getFinalCreator());

        return new ObjectResponse<>(groupResponse);
    }

    @GetMapping(value = "/info")
    @ApiOperation(
            value = "고객정보",
            notes = "고객정보 API")
    public ObjectResponse<Customer> info(InfoCustomerRequest request) throws ServiceException {
        return new ObjectResponse<>(customerService.getCustomer(request));
    }
}
