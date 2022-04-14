package com.quokka.catalog.service.impl;

import com.quokka.catalog.model.Category;
import com.quokka.catalog.repository.CategoryRepository;
import com.quokka.catalog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCateory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(String categoryCode) {
        return categoryRepository.findById(categoryCode).get();
    }

    @Override
    public Category getAllActiveCategory(Boolean isActive) {
        return categoryRepository.findAllByIsActive(isActive);
    }

    @Override
    public Category updateCategory(String categoryCode) {
        return null;
    }
}
