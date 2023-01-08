package com.scm.service;

import com.scm.domain.Retailer;
import com.scm.dto.request.RegisterRequest;
import com.scm.exception.ConflictException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;

    public void saveUser(RegisterRequest registerRequest) {
        if (retailerRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

        Retailer retailer = new Retailer();
        retailer.setName(registerRequest.getName());
        retailer.setPassword(registerRequest.getPassword());
        retailer.setEmail(registerRequest.getEmail());
        retailer.setPhoneNumber(registerRequest.getPhoneNumber());

        retailerRepository.save(retailer);
    }
}
