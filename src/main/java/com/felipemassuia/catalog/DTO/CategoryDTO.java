package com.felipemassuia.catalog.DTO;

import com.felipemassuia.catalog.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
	
	private Long id;
	private String name;
	
	public CategoryDTO (Category cat) {
		this.id = cat.getId();
		this.name = cat.getName();
	}

}
