package com.scm.controller;

import com.scm.dto.request.ProductsRequest;
import com.scm.dto.response.ResponseMessage;
import com.scm.dto.response.SCMResponse;
import com.scm.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/retailer/{imageId}/add")
    public ResponseEntity<SCMResponse> saveCar(@PathVariable String imageId, @Valid @RequestBody ProductsRequest productsRequest) {
        productsService.saveProducts(imageId, productsRequest);
        SCMResponse response = new SCMResponse(ResponseMessage.PRODUCT_SAVED_RESPONSE_MESSAGE, true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/visitors/{name}")
    public ResponseEntity<ProductsRequest> getProductByName(@PathVariable String name) {
        ProductsRequest productsRequest = productsService.findByName(name);
        return ResponseEntity.ok(productsRequest);
    }

    @GetMapping("/visitors/all")
    public ResponseEntity<List<ProductsRequest>> getAllProducts() {
        List<ProductsRequest> allProducts = productsService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/visitors/pages")
    public ResponseEntity<Page<ProductsRequest>> getAllProductsWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<ProductsRequest> productsRequestPage = productsService.findAllWithPage(pageable);
        return ResponseEntity.ok(productsRequestPage);
    }

    @PutMapping("/supplier/update")
    public ResponseEntity<SCMResponse> updateProduct(
            @RequestParam("id") String name,
            @RequestParam("imageId") String imageId,
            @Valid @RequestBody ProductsRequest productsRequest) {
        productsService.updateProduct(name, imageId, productsRequest);
        SCMResponse response = new SCMResponse(ResponseMessage.PRODUCT_UPDATE_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(response);
    }
}
