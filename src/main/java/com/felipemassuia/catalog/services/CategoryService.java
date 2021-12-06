package com.felipemassuia.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemassuia.catalog.DTO.CategoryDTO;
import com.felipemassuia.catalog.entities.Category;
import com.felipemassuia.catalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryDTO> findAll() {
		
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		return listDTO;
	}
	
}
