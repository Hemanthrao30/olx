//package com.stackroute.service.Repository;
//
//import com.stackroute.Model.IncomingProductData;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ProductDataRepositoryTest {
//    @Autowired
//private ProductDataRepositoryTest productDataRepositoryTest;
//
//    @Test
//    void getProductRecommendationByLocation() {
//        IncomingProductData location1=new IncomingProductData("1","S21",7899.332,"mobile","Sikkim","Gangtok");
//        IncomingProductData addedlocation1= productDataRepositoryTest.save(location1);
//        assertEquals(location1.getProductId(),addedlocation1.getProductId(),"New Location should be saved and the same should be returned");
//    }
//
//    private IncomingProductData save(IncomingProductData location1) {
//        return location1;
//    }
//
//}
