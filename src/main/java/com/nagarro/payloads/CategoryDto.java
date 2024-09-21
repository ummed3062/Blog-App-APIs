package com.nagarro.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min = 4)
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10)
	private String categoryDescription;


}
