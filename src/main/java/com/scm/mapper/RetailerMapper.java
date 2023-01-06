package com.scm.mapper;

import com.scm.domain.Retailer;
import com.scm.dto.request.RetailerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetailerMapper {

    @Mapping(target = "id", ignore = true)
    Retailer retailerRequestToRetailer(RetailerRequest retailerRequest);
}
