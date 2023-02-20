package com.stackroute.productservice.model;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class IncomingProductData {
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

    private String brand;
    public String color;
    public String yearsOfUse;
    public String warranty;
    public String productDefects;
    @JsonEnumDefaultValue
    public Status pstatus = Status.AVAILABLE;
    private String purchaseDate;

}
