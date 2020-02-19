package com.zzanggar.service;

import com.zzanggar.constants.SearchRule;
import com.zzanggar.entity.Customer;
import com.zzanggar.exception.ServiceException;
import com.zzanggar.model.customer.EditCustomerRequest;
import com.zzanggar.model.customer.InfoCustomerRequest;
import com.zzanggar.model.customer.RegisterCustomerRequest;
import com.zzanggar.response.ListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    ListResponse list(SearchRule rule, String keyword, Pageable pageable);

    Page<Customer> listAll(int size);

    Page<Customer> listAllByDate(Date startDate, Date endDate, int size);

    Customer edit(long customerIdx, EditCustomerRequest editCustomerRequest) throws ServiceException;

    Customer register(Customer customer) throws ServiceException;

    Customer register(RegisterCustomerRequest registerCustomerRequest) throws ServiceException;

    Customer delete(long customerIdx) throws ServiceException;

    List<Customer> delete(List<Long> idxes) throws ServiceException;

    List<String> getBusinessCategoryList() throws ServiceException;

    List<String> getPositionList() throws ServiceException;

    List<String> getDepartmentList() throws ServiceException;

    List<String> getFinalCreator() throws ServiceException;

    Customer getCustomer(long customerIdx) throws ServiceException;

    Customer getCustomerByLicense(String licenseNumber) throws ServiceException;

    Customer getCustomerByEmail(String email) throws ServiceException;

    Customer getCustomer(InfoCustomerRequest request) throws ServiceException;
}
