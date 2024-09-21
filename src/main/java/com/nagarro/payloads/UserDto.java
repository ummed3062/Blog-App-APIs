package com.nagarro.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nagarro.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message = "Email is not valid. Please Try agin !")
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 8,message = "Password must be min of 3 char and max of 8 char !")
//	@Pattern(regexp = )   we can use this annotation for pattern like: only use number, at least one special char etc.
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
