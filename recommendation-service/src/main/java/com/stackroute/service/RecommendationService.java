
package com.stackroute.service;

import com.stackroute.Exception.ProductAlreadyExistException;
import com.stackroute.Exception.ProductNotFoundException;
import com.stackroute.Model.IncomingProductData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public interface RecommendationService {
    void deleteProductNode(String productId);
    void  createNode(IncomingProductData incomingProductData)throws ProductAlreadyExistException;
    HashSet<IncomingProductData> getProductRecommendationsByLocation(String state)throws ProductNotFoundException;
    HashSet<IncomingProductData> getProductRecommendationByStateAndCategory(String state, String category)throws ProductNotFoundException;
    HashSet<IncomingProductData> getProductByCategory(String category)throws ProductNotFoundException;
    public ArrayList<IncomingProductData> getAllProduct();

    IncomingProductData getById(String i) throws ProductNotFoundException;

    IncomingProductData addNew(IncomingProductData data);
    //IncomingProductData getById(String i) throws ProductNotFoundException;

}