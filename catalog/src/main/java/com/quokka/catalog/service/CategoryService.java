package com.quokka.catalog.service;

import com.quokka.catalog.model.Category;

public interface CategoryService {
    Category createCateory(Category category);
    Category getById(String categoryCode);
    Category getAllActiveCategory(Boolean isActive);
    Category updateCategory(String categoryCode);
}
