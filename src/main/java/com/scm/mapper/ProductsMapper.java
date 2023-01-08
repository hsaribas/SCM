package com.scm.mapper;

import com.scm.domain.ImageFile;
import com.scm.domain.Products;
import com.scm.dto.request.ProductsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    @Mapping(target = "image", ignore = true)
    Products productsRequestToProducts(ProductsRequest productsRequest);

    List<ProductsRequest> map(List<Products> products);

    @Mapping(source = "image", target = "image", qualifiedByName = "getImageAsString")
    ProductsRequest productsToProductsRequest(Products products);

    @Named("getImageAsString")
    public static Set<String> getImageIds(Set<ImageFile> imageFiles) {
        Set<String> imgs = new HashSet<>();
        imgs = imageFiles.stream().map(imFile -> imFile.getId().toString()).collect(Collectors.toSet());
        return imgs;
    }
}
