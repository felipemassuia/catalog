package com.felipemassuia.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemassuia.catalog.entities.Category;
import com.felipemassuia.catalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll (){
		return categoryRepository.findAll();
	}
	
}
