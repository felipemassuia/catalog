package com.felipemassuia.catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipemassuia.catalog.DTO.CategoryDTO;
import com.felipemassuia.catalog.entities.Category;
import com.felipemassuia.catalog.repositories.CategoryRepository;
import com.felipemassuia.catalog.services.exceptions.DatabaseException;
import com.felipemassuia.catalog.services.exceptions.ResourceNotFoundException;

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
		
		CategoryDTO dtoCategory = new CategoryDTO(optionalCategory.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
		
		return dtoCategory;
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category category = new Category();
		category.setName(dto.getName());
		category = categoryRepository.save(category);
		
		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		Category cat = categoryRepository.getOne(id);
		
		
		try {
		cat.setName(dto.getName());
		cat = categoryRepository.save(cat);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		
		return new CategoryDTO(cat);
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);

		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(PageRequest pageRequest) {
		
		Page<Category> list = categoryRepository.findAll(pageRequest);
		Page<CategoryDTO> listDTO = list.map(x -> new CategoryDTO(x));
		return listDTO;
	}
	
}
