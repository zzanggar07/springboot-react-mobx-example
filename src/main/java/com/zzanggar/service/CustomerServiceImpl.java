package com.zzanggar.service;


import com.zzanggar.constants.SearchRule;
import com.zzanggar.constants.ServerError;
import com.zzanggar.entity.Customer;
import com.zzanggar.exception.ServiceException;
import com.zzanggar.model.customer.EditCustomerRequest;
import com.zzanggar.model.customer.InfoCustomerRequest;
import com.zzanggar.model.customer.RegisterCustomerRequest;
import com.zzanggar.repository.CustomerRepository;
import com.zzanggar.response.ListResponse;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ModelMapper modelMapper;

    @Resource
    private CustomerRepository customerRepository;

    @Override
    public ListResponse list(SearchRule rule, String keyword, Pageable pageable) {
        if (!StringUtils.isBlank(keyword)) {
            switch (rule) {
                case COMPANY_NAME:
                    return modelMapper.map(customerRepository.findAllByCompanyNameContaining(keyword, pageable), ListResponse.class);
                case EMAIL:
                    return modelMapper.map(customerRepository.findAllByEmailContaining(keyword, pageable), ListResponse.class);
                case MANAGER_NAME:
                    return modelMapper.map(customerRepository.findAllByManagerNameContaining(keyword, pageable), ListResponse.class);
                case FINAL_CREATOR:
                    return modelMapper.map(customerRepository.findAllByFinalCreatorContaining(keyword, pageable), ListResponse.class);
            }
        }

        return modelMapper.map(customerRepository.findAll(pageable), ListResponse.class);
    }

    @Override
    public Page<Customer> listAll(int size) {
        return customerRepository.findAllByOrderByCreatedTime(PageRequest.of(0, size));
    }

    @Override
    public Page<Customer> listAllByDate(Date startDate, Date endDate, int size) {
        if (startDate == null && endDate == null) {
            return listAll(size);
        } else {
            PageRequest pageRequest = PageRequest.of(0, size);
            if (startDate != null && endDate == null) {
                return customerRepository.findAllByCreatedTimeGreaterThanEqualOrderByCreatedTime(startDate, pageRequest);
            } else if (startDate == null) {
                return customerRepository.findAllByCreatedTimeLessThanEqualOrderByCreatedTime(endDate, pageRequest);
            } else {
                return customerRepository.findAllByCreatedTimeIsBetweenOrderByCreatedTime(startDate, endDate, pageRequest);
            }
        }
    }

    @Override
    public Customer edit(long customerIdx, EditCustomerRequest editCustomerRequest) throws ServiceException {
        Customer customer = getCustomer(customerIdx);
        if (customerRepository.existsByEmailAndIdxNot(editCustomerRequest.getEmail(), customerIdx)) {
            throw new ServiceException(ServerError.ALREADY_REGISTERED_EMAIL_CUSTOMER);
        }
        modelMapper.map(editCustomerRequest, customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer register(Customer customer) throws ServiceException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ServiceException(ServerError.ALREADY_REGISTERED_USER);
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer register(RegisterCustomerRequest registerCustomerRequest) throws ServiceException {
        return register(modelMapper.map(registerCustomerRequest, Customer.class));
    }

    @Override
    public Customer delete(long customerIdx) throws ServiceException {
        Customer customer = getCustomer(customerIdx);
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public List<Customer> delete(List<Long> idxes) throws ServiceException {
        List<Customer> customers = customerRepository.findAllById(idxes);
        if (customers.size() == 0) {
            throw new ServiceException(ServerError.NOT_EXIST_CUSTOMER);
        }
        customerRepository.deleteAllByIdxInQuery(idxes);
        return customers;
    }

    @Override
    public List<String> getBusinessCategoryList() {
        return customerRepository.findAllBusinessCategoryQuery();
    }

    @Override
    public List<String> getPositionList() {
        return customerRepository.findAllPositionQuery();
    }

    @Override
    public List<String> getDepartmentList() {
        return customerRepository.findAllDepartmentQuery();
    }

    @Override
    public List<String> getFinalCreator() {
        return customerRepository.findAllFinalCreatorQuery();
    }

    @Override
    public Customer getCustomer(long customerIdx) throws ServiceException {
        return customerRepository.findById(customerIdx).orElseThrow(() -> new ServiceException(ServerError.NOT_EXIST_CUSTOMER));
    }

    @Override
    public Customer getCustomerByLicense(String licenseNumber) {
        return customerRepository.findFirstByLicenseNumber(licenseNumber).orElseGet(Customer::new);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findFirstByEmail(email).orElseGet(Customer::new);
    }

    @Override
    public Customer getCustomer(InfoCustomerRequest request) {
        if (request.getEmail() != null) {
            return getCustomerByEmail(request.getEmail());
        }
        if (request.getLicenseNumber() != null) {
            return getCustomerByLicense(request.getLicenseNumber());
        }
        return getCustomerByEmail(request.getEmail());
    }
}
