package com.example.redis.controller;

import com.example.redis.service.PlaFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/*")
public class PlaFeedController {

    @Autowired
    private PlaFeedService plaFeedService;

    @GetMapping("getPlaFeed")
    public String getPlafeed(@RequestParam Map<String,String> map){
      return   plaFeedService.findByColumn(map);
    }
}
