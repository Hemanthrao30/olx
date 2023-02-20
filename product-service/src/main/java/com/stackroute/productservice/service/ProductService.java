package com.stackroute.productservice.service;

import com.stackroute.productservice.exception.NoProductExistsInTheRepository;
import com.stackroute.productservice.exception.ProductAlreadyExistException;
import com.stackroute.productservice.exception.ProductNotFoundException;
import com.stackroute.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Iterable<Product> findallrepository() throws NoProductExistsInTheRepository;

    public Product addprod(Product product) throws ProductAlreadyExistException;


    Product getByProductId(String productId) throws ProductNotFoundException;
    List<Product> findBySellerEmail(String sellerEmail) throws ProductNotFoundException;

    boolean deleteProductByProductId (String productId) throws ProductNotFoundException;




}
