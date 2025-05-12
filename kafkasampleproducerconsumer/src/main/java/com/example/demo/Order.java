package com.example.demo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private String orderID;

    private Date dateOfCreation;

    private String content;

}
