package com.learning.backend.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;
import com.learning.backend.Repository.UserRepository;
import com.learning.backend.Service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

//	@Override
//	public User add(User user) {
//		return userRepository.save(user);
//	}
	@Override
	public UserDto login(String email, String password) {

		UserDto resultDto = new UserDto();

		// get user data based on email
		User user = userRepository.findByEmail(email);

		if (user != null) {
			if (!user.getEmail().equals(email)) {
				resultDto.setMessage("invalid email");
				return resultDto;
			}
			if (!user.getPassword().equals(password)) {
				resultDto.setMessage("invalid password");
			}
			resultDto.setId(user.getId());
			resultDto.setName(user.getName());
			resultDto.setUserName(user.getUserName());
			resultDto.setMessage("login success");
			resultDto.setStatus(0);
		} else {
			resultDto.setStatus(1);
			resultDto.setMessage("login failed");
		}
		;

		return resultDto;
	}

}
