package com.platform.blogging.service.impl;

import com.platform.blogging.dto.CategoryDTO;
import com.platform.blogging.entity.Category;
import com.platform.blogging.exceptions.ResourceNotFoundException;
import com.platform.blogging.repository.CategoryRepo;
import com.platform.blogging.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CategoryserviceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> listOfCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category c:categoryList){
            System.out.println(c.toString()+" "+c.getId());
        }
        Type listType = new TypeToken<List<CategoryDTO>>() {}.getType();
        List<CategoryDTO> categoryDTOList = modelMapper.map(categoryList, listType);
        return categoryDTOList;
    }

    @Override
    public CategoryDTO deleteCategoryById(int id) {
       Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+ "Category not found"));
        categoryRepo.delete(category);
       return  modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(categoryId+" category not found"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+" category not found"));
        return modelMapper.map(category,CategoryDTO.class);
    }
}
