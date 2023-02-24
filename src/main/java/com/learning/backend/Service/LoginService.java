package com.learning.backend.Service;

import com.learning.backend.Dto.UserDto;


public interface LoginService {

	UserDto login(String email, String password);

}
