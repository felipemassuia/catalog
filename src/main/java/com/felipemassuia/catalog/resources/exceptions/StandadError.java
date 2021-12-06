package com.felipemassuia.catalog.resources.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandadError {
	
	private Instant timestamp;
	private Integer code;
	private String error;
	private String message;
	private String URI;
	
}
