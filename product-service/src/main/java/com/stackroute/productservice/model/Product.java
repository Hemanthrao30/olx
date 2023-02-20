package com.stackroute.productservice.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "my-products")
public class Product {




    @Id
    private String productId;

    //@Field(type = FieldType.Text, name = "name")
    private String productName;
    //@Field(type = FieldType.Double, name = "price")
    private double productPrice;
    private String productCondition;
    private String additionalDetails;
    private String productDescription;
    //    private String email;
    private String city;
    private String state;
    private String category;
    private byte[] image;
    private String sellerName;
    private String pincode;
    private String sellerEmail;
    private String sellerState;

    private String sellerMobile;
    private String purchaseDate;

    private String brand;
    public String color;
    public String yearsOfUse;
    public String warranty;
    public String productDefects;
    @JsonEnumDefaultValue
    public Status pstatus = Status.AVAILABLE;
    private Date productdate = new Date(System.currentTimeMillis());




}
