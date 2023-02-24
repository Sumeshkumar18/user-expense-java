package com.learning.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;
import com.learning.backend.Service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public UserDto addUser(@RequestBody UserDto userDto) {
		UserDto resultDto = new UserDto();
		resultDto = userService.add(userDto);
		return resultDto;
	}
	
	@GetMapping("/getAllUsers")
	public List<User>getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/updateUser/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto,@PathVariable String id){
		return userService.updateUser(userDto,id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
		public User deleteUser(@PathVariable Long id) {
			return userService.deleteUser(id);
		}
	}
