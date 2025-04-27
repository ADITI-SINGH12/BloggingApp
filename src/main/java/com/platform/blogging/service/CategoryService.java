package com.platform.blogging.service;

import com.platform.blogging.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> listOfCategory();
    CategoryDTO deleteCategoryById(int id);
    CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);
    CategoryDTO getCategoryById(int id);
}
