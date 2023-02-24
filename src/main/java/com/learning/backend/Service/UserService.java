package com.learning.backend.Service;

import java.util.List;

import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;

public interface UserService {

	UserDto add(UserDto userDto);

	List<User> getAllUsers();

	User getUserById(Long id);

//	User updateUser(User newUser, Long id);

	User deleteUser(Long id);

	UserDto updateUser(UserDto userDto, String id);

//	UserDto add(UserDto userDto);
;
}
