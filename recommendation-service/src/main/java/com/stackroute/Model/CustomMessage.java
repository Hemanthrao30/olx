package com.stackroute.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CustomMessage {
    private String productId;
    private String productName;
    private Double productPrice;
    private String category;
    private String state;
    private String city;
   private byte[] productImage;

   public CustomMessage(String productId, String productName, String category, String state, String city, byte[] productImage) {

    }

   // public CustomMessage(String productId, String productName, String category, String state, String city) {
    //}
}