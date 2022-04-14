package com.quokka.catalog.controller;

import com.quokka.catalog.model.Category;
import com.quokka.catalog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/*")
public class CategoryController {
    private static final Logger logger= LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "createCategory",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public Category createCategory(@RequestBody Category category){
        logger.info("Got Request for the creation of category : "+category.toString());
        return categoryService.createCateory(category);
    }
    @RequestMapping(value = "getById/{categoryCode}",method = RequestMethod.GET,consumes ="application/json",produces = "application/json")
    public Category getById(@PathVariable("categoryCode")String categoryCode){
        logger.info("Got Request for the category by categoryCode : "+categoryCode);
        return categoryService.getById(categoryCode);
    }

}
