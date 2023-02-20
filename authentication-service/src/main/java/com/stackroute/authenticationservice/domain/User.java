package com.stackroute.authenticationservice.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @NotNull
    @NotBlank
    @Email
    private String email;

//    @NotNull
//    @Size(min=2, message = "First Name should have atleast 2 characters")
    private String firstname;


//    @NotNull
//    @Size(min=8, message = "First Name should have atleast 2 characters")
    private String password;

//    @NotNull
//    @Size(min=8, message = "First Name should have atleast 2 characters")
    private String cpassword;

    private String street;

    private String city;

    private String state;

    private String pincode;



//    @NotNull
//    @Size(min=10, max=10, message = "First Name should have atleast 2 characters")
    private String contactNo;


//    @NotNull
//    @Size(min=8, max=10, message = "First Name should have atleast 2 characters")
    private int alternateNo;

    private byte[] image;

//    public User(String email, String firstname, String lastname, String password, String cpassword, String street, String city, String state, String pincode, long contactNo, int alternateNo) {
//        this.email = email;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.password = password;
//        this.cpassword = cpassword;
//        this.address = address;
//        this.contactNo = contactNo;
//        this.alternateNo = alternateNo;
//    }
}

