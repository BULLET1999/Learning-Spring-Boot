package com.learning.spring.controller;

import com.learning.spring.model.Product;
import com.learning.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @Autowired
    ProductService productService;

    //Re-direct to home page
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/product/get-products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(
    ) {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/create-product", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(
            @RequestBody Product product
    ) {
        productService.createProduct(product);
        return new ResponseEntity<>("Product is Created Successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/delete-product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(
            @PathVariable(value = "id") String id
    ) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted Successfully.", HttpStatus.OK);
    }

    @RequestMapping(value = "/product/update-product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(
            @PathVariable(value = "id") String id,
            @RequestBody Product product
    ) {
        productService.updateProduct(id, product);
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}
