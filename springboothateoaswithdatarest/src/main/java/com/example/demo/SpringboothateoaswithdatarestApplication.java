package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * http://localhost:8080/products/products?page=0&size=1
 * 
 * 
 * 
 * 
 * {
  "_embedded" : {
    "products" : [ {
      "name" : "abc1",
      "price" : 20.0,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/products/products/1"
        },
        "product" : {
          "href" : "http://localhost:8080/products/products/1"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/products/products?page=0&size=1"
    },
    "profile" : {
      "href" : "http://localhost:8080/products/profile/products"
    }
  },
  "page" : {
    "size" : 1,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
@SpringBootApplication
public class SpringboothateoaswithdatarestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboothateoaswithdatarestApplication.class, args);
	}

}
