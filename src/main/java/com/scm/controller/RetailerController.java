package com.scm.controller;

import com.scm.dto.request.RegisterRequest;
import com.scm.dto.response.ResponseMessage;
import com.scm.dto.response.SCMResponse;
import com.scm.exception.SCMExceptionHandler;
import com.scm.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/retailer")
public class RetailerController {

    @Autowired
    private RetailerService retailerService;

    @PostMapping("/register")
    private ResponseEntity<SCMResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        retailerService.saveUser(registerRequest);

        SCMResponse response = new SCMResponse();
        response.setMessage(ResponseMessage.RETAILER_REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
