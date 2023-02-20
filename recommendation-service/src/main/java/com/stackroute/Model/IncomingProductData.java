package com.stackroute.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;



@Node
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomingProductData {
    @Id
    private String productId;
    private String productName;
    private Double productPrice;
    private String category;
    private String state;
    private String city;
    private byte[] image;

//    public IncomingProductData(String productId, String state, String city, String category, byte[] productImage) {
//        this.productId = productId;
//        this.state = state;
//        this.city = city;
//        this.category = category;
//        this.productImage=productImage;
//    }

//    public IncomingProductData(String productId, String productName, String state, String city, String category) {
//        this.productId = productId;
//      //  this.productOwnerEmail = productOwnerEmail;
//        this.productName = productName;
//        this.state = state;
//        this.city = city;
//        this.category = category;
//    }
//    private String productId;
//
//    //@Field(type = FieldType.Text, name = "name")
//    private String productName;
//    //@Field(type = FieldType.Double, name = "price")
//    private double productPrice;
//    private String productCondition;
//    private String additionalDetails;
//    private String productDescription;
//    //    private String email;
////    private String state;
//    private String category;
//    private byte[] image;
//    private String sellerName;
//    private String pincode;
//    private String sellerEmail;
//    private String sellerState;
//
//    private String brand;
//    public String color;
//    public String yearsOfUse;
//    public String warranty;
//    public String productDefects;
//    @JsonEnumDefaultValue
//    public Status pstatus = Status.AVAILABLE;
}
