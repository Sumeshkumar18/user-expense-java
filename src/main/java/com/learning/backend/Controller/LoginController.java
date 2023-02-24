package com.learning.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;
import com.learning.backend.Service.LoginService;

@CrossOrigin("*")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/loginUser")
	public UserDto loginUser(@RequestBody UserDto userDto) {
		UserDto resultDto = new UserDto();
		resultDto = loginService.login(userDto.getEmail(),userDto.getPassword());
		return resultDto;
	}
}
