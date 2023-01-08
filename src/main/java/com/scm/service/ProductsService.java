package com.scm.service;

import com.scm.domain.ImageFile;
import com.scm.domain.Products;
import com.scm.dto.request.ProductsRequest;
import com.scm.exception.ConflictException;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.mapper.ProductsMapper;
import com.scm.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ImageFileService imageFileService;

    public void saveProducts(String ImageId, ProductsRequest productsRequest) {
        ImageFile imageFile = imageFileService.findImageById(ImageId);

        Products products = productsMapper.productsRequestToProducts(productsRequest);
        Set<ImageFile> imFiles = new HashSet<>();
        imFiles.add(imageFile);
        products.setImage(imFiles);

        productsRepository.save(products);
    }

    public List<ProductsRequest> getAllProducts() {
        List<Products> productsList = productsRepository.findAll();
        return productsMapper.map(productsList);
    }

    public Page<ProductsRequest> findAllWithPage(Pageable pageable) {
        Page<Products> productsPage = productsRepository.findAll(pageable);
        Page<ProductsRequest> productsRequestPage = productsPage.map(new Function<Products, ProductsRequest>() {
            @Override
            public ProductsRequest apply(Products products) {
                return productsMapper.productsToProductsRequest(products);
            }
        });
        return productsRequestPage;
    }

    public ProductsRequest findByName(String name) {
        Products products = getProduct(name);
        return productsMapper.productsToProductsRequest(products);
    }

    public Products getProduct(String name) {
        Products product = productsRepository.findProductsByName(name).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, name)));
        return product;
    }

    public void updateProduct(String name, String imageId, ProductsRequest productsRequest) {
        Products product = getProduct(name);

        ImageFile imageFile = imageFileService.findImageById(imageId);
        List<Products> productsList = productsRepository.findProductsByImageId(imageFile.getId());

        for (Products p : productsList) {
            if (product.getId().longValue() != p.getId().longValue()) {
                throw new ConflictException(ErrorMessage.IMAGE_USED_MESSAGE);
            }
        }

        product.setProductName(productsRequest.getProductName());
        product.setQuantity(productsRequest.getQuantity());
        product.setPrice(productsRequest.getPrice());

        product.getImage().add(imageFile);

        productsRepository.save(product);
    }
}
