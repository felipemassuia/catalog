package com.felipemassuia.catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipemassuia.catalog.DTO.CategoryDTO;
import com.felipemassuia.catalog.entities.Category;
import com.felipemassuia.catalog.repositories.CategoryRepository;
import com.felipemassuia.catalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		return listDTO;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		CategoryDTO dtoCategory = new CategoryDTO(optionalCategory.orElseThrow(() -> new EntityNotFoundException("Entity not found")));
		
		return dtoCategory;
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category category = new Category();
		category.setName(dto.getName());
		category = categoryRepository.save(category);
		
		return new CategoryDTO(category);
	}
	
}
