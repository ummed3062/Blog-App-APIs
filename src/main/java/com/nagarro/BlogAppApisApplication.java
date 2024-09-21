package com.nagarro;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nagarro.config.AppConstatnts;
import com.nagarro.entities.Role;
import com.nagarro.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

//	@Bean Annotation use for create a bean By which we can use any object of a class using @Autowired Annotation.
//	exploits the Inversion of Control feature by which an object defines its dependencies without creating them
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setId(AppConstatnts.ADMIN_USER);
			role.setRoleName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstatnts.NORMAL_USER);
			role1.setRoleName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role, role1);
			
			List<Role> savedRoles = this.roleRepo.saveAll(roles);
			
			savedRoles.forEach(r->{
				System.out.println(r.getRoleName());
			});
		} catch (Exception e) {

			e.printStackTrace();
		}
		

	}

}
