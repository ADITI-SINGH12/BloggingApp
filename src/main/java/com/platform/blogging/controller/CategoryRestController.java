package com.platform.blogging.controller;

import com.platform.blogging.dto.CategoryDTO;
import com.platform.blogging.repository.CategoryRepo;
import com.platform.blogging.service.CategoryService;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/categories")
public class CategoryRestController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedcategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedcategoryDTO, HttpStatus.CREATED);
    }
    @GetMapping("/categoryList")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
       return new ResponseEntity<>(categoryService.listOfCategory(),HttpStatus.OK);
    }
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategoryById(@PathVariable int categoryId) {
    return new ResponseEntity<>(categoryService.deleteCategoryById(categoryId),HttpStatus.OK);
    }
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategoryDetail(@RequestBody CategoryDTO categoryDTO,@PathVariable int categoryId){
        CategoryDTO updateCategory = categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
}
