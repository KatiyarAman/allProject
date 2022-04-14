package com.quokka.catalog.controller;

import com.google.gson.Gson;
import com.quokka.catalog.model.Product;
import com.quokka.catalog.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "createProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product product) {
        logger.info("Got the request to persist the entity :" + new Gson().toJson(product));
        return productService.createProduct(product,"aman");
    }
    @RequestMapping(value = "getById/{idProduct}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public Product getById(@PathVariable("idProduct")String idProduct) {
        logger.info("Got the request to get the entity by productId :" + new Gson().toJson(idProduct));
        return productService.getById(idProduct);
    }
    @RequestMapping(value = "findCustom/{idProduct}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<Product> createProduct(@PathVariable("idProduct")Boolean idProduct) {
        logger.info("Got the request to get the entity by working zipCode :" + new Gson().toJson(idProduct));
        return productService.findAllByColumnName("active",idProduct);
    }
}
