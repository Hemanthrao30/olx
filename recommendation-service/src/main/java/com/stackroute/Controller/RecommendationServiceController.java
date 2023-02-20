package com.stackroute.Controller;
import com.stackroute.Exception.ProductAlreadyExistException;
import com.stackroute.Exception.ProductNotFoundException;
import com.stackroute.Model.CustomMessage;
import com.stackroute.Model.IncomingProductData;
import com.stackroute.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;


@RestController
@RequestMapping("api/v1")
@Slf4j
public class RecommendationServiceController {

    @Autowired
    RecommendationService recommendationService;



    @PostMapping("/add")
    public ResponseEntity<?> addIncomingData(@RequestBody IncomingProductData incomingProductData) {
        try {
            CustomMessage messages = new CustomMessage(incomingProductData.getProductId(), incomingProductData.getProductName(), incomingProductData.getCategory(),
                    incomingProductData.getState(), incomingProductData.getCity(), incomingProductData.getImage());
            this.recommendationService.createNode(incomingProductData);
            return new ResponseEntity<>("Added data to neo4j successfully!", HttpStatus.OK);
        } catch (ProductAlreadyExistException e) {
            log.error("(ProductAlreadyExist", e);
            return new ResponseEntity("Product already Exist", org.springframework.http.HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/recommend/{state}")
    public ResponseEntity<?> getProductRecommendationsByLocation(@PathVariable String state) {
        try {
            HashSet<IncomingProductData> recommendations = this.recommendationService.getProductRecommendationsByLocation(state);
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity("Product does Not Exits", HttpStatus.CONFLICT);
        }
    }


//    @GetMapping("/recommend/{state}")
//    public ResponseEntity<?> getProductRecommendationsByLocation(@PathVariable String state) throws ProductNotFoundException {
//     //   try {
//    this.recommendationService.getProductRecommendationsByLocation(state);
//            return new ResponseEntity<>("state added ", HttpStatus.OK);
//    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductNode(@PathVariable String productId) throws ProductNotFoundException {
        this.recommendationService.deleteProductNode(productId);
        return new ResponseEntity<>("Product is delete successfully", HttpStatus.OK);
    }

    @GetMapping("/productByCategory/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String category) {//throws ProductNotFoundException{
        try {
            HashSet<IncomingProductData> recommendations = this.recommendationService.getProductByCategory(category);
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity("Product does Not Exits", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/productByStateAndCategory/{state}/{category}")
    public ResponseEntity<?> getProductRecommendationByStateAndCategory(@PathVariable String state, @PathVariable String category) throws ProductNotFoundException{
        try {
            HashSet<IncomingProductData> recommendations = this.recommendationService.getProductRecommendationByStateAndCategory(state, category);
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity("Product does Not Exits", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        ArrayList<IncomingProductData> rec = this.recommendationService.getAllProduct();
        return new ResponseEntity<>(rec, HttpStatus.OK);
    }
}
