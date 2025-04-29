package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Product;
/**
 * 
 * http://localhost:8080/products/products?page=1&size=0
 * 
 * {
  "_embedded" : {
    "products" : [ ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/products/products?page=0&size=20"
    },
    "prev" : {
      "href" : "http://localhost:8080/products/products?page=0&size=20"
    },
    "self" : {
      "href" : "http://localhost:8080/products/products?page=1&size=20"
    },
    "last" : {
      "href" : "http://localhost:8080/products/products?page=0&size=20"
    },
    "profile" : {
      "href" : "http://localhost:8080/products/profile/products"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 1
  }
}
 * 
 * 
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
