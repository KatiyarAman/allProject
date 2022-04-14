package com.db.multi.controller;

import com.db.multi.mongo.UserDetails;
import com.db.multi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo/*")
public class UserDetail {
    @Autowired
    UserDetailService service;

    @PostMapping(value = "",consumes = "application/json",produces = "application/json")
    public UserDetails save(@RequestBody UserDetails userDetails){
        return service.save(userDetails);
    }


    @GetMapping(value = "getById/{id}",consumes = "application/json",produces = "application/json")
    public UserDetails save(@PathVariable("id")Long id){
        return service.getById(id);
    }
}
