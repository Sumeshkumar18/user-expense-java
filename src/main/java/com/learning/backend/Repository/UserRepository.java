package com.learning.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	UserDto save(UserDto userDto);

	User findByEmail(String email);



}
