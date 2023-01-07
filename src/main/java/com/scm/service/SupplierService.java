package com.scm.service;

import com.scm.domain.Retailer;
import com.scm.domain.Supplier;
import com.scm.dto.request.RegisterRequest;
import com.scm.exception.ConflictException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public void saveUser(RegisterRequest registerRequest) {
        if (supplierRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

        Supplier supplier = new Supplier();
        supplier.setName(registerRequest.getName());
        supplier.setPassword(registerRequest.getPassword());
        supplier.setEmail(registerRequest.getEmail());
        supplier.setPhoneNumber(registerRequest.getPhoneNumber());

        supplierRepository.save(supplier);
    }
}
