package edu.guilford;

import java.security.MessageDigest;

//This class is used to encrypt the message the user entered
//that will then be transported into the image the user chose
public class Crypto {

    //Attributes used to encrypt the message
    // private String message;

    //Attributes
    MessageDigest md;
    byte[] digest;
    StringBuffer hexString;

    //Constructor
    public Crypto() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
}
