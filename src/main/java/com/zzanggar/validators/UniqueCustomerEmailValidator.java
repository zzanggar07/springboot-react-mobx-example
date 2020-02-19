package com.zzanggar.validators;

import com.zzanggar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCustomerEmailValidator implements ConstraintValidator<UniqueCustomerEmail, String> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniqueCustomerEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !customerRepository.existsByEmail(email);
    }
}
