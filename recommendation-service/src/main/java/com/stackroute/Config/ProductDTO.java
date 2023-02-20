package com.stackroute.Config;

import lombok.*;
//import org.hibernate.validator.internal.xml.binding.FieldType;
import org.springframework.data.neo4j.core.schema.Id;

//import java.util.Date;


@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    @Id
    private String productId;
    private String productName;
    private double productPrice;
    private String city;
    private String state;
    private String category;
    private byte[] image;


}
    //@Field(type = FieldType.Text, name = "name")
    //@Field(type = FieldType.Double, name = "price")
//    private String productCondition;
//    private String additionalDetails;
//    private String productDescription;
//    //    private String email;
//
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
//
//    private Date productdatelocal;
//
//}