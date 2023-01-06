package com.scm.service;

import com.scm.domain.Retailer;
import com.scm.repository.RetailerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RetailerService {

    private RetailerRepository retailerRepository;

    public void saveRetailer(Retailer retailer) {

        retailerRepository.save(retailer);
    }
}
