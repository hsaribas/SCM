package com.scm.controller;

import com.scm.domain.Retailer;
import com.scm.dto.request.RetailerRequest;
import com.scm.dto.response.SCMResponse;
import com.scm.mapper.RetailerMapper;
import com.scm.service.RetailerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retailer")
@AllArgsConstructor
public class RetailerController {

    private RetailerService retailerService;

    private RetailerMapper retailerMapper;

    @PostMapping("/member")
    public ResponseEntity<SCMResponse> createRetailer(@Valid @RequestBody RetailerRequest retailerRequest) {

        Retailer retailer = retailerMapper.retailerRequestToRetailer(retailerRequest);
        retailerService.saveRetailer(retailer);

        SCMResponse response = new SCMResponse("New retailer successfully created", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
