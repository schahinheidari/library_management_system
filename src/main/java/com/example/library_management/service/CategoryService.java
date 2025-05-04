package com.example.library_management.service;

import com.example.library_management.entity.Book;
import com.example.library_management.entity.Category;
import com.example.library_management.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public Category getCategoryById(int id){
        return categoryDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    public Category save(Category category){
        return categoryDao.save(category);
    }

    public Category update(int id, Category updateCategory){
        return categoryDao.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updateCategory.getName());
                    return categoryDao.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("ID Not Found" + id));
    }

    public void deleteCategoryById(int id){
        categoryDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
        categoryDao.deleteById(id);
    }
}
