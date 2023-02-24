package com.learning.backend.ServiceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.backend.Utils;
import com.learning.backend.Dto.UserDto;
import com.learning.backend.Entity.User;
import com.learning.backend.Exception.UserNotFoundException;
import com.learning.backend.Repository.UserRepository;
import com.learning.backend.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto add(UserDto userDto) {
		UserDto resultDto = new UserDto();
		User user = new User();
		user.setName(userDto.getName());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(Utils.generateUUID(7));
		user.setCreatedBy(userDto.getCreatedBy());
		user.setCreatedDate(userDto.getCreatedDate());
		user.setUpdatedBy(userDto.getUpdatedBy());
		user.setUpdatedDate(userDto.getUpdatedDate());
		
		userRepository.save(user);
		resultDto.setStatus(1);
		resultDto.setMessage("user added");
		return resultDto;
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}

//	@Override
//	public User updateUser(User user, Long id) {
//		
//		return userRepository.findById(id).map( user ->{
//			user.setName(user.getName());
//			user.setUserName(user.getUserName());
//			user.setEmail(user.getEmail());
//			userRepository.save(user);
//		}).orElseThrow(() ->new UserNotFoundException(id));
//	 }

	@Override
	public UserDto updateUser(UserDto userDto, String id) {
	
		UserDto resultDto = new UserDto();
		User user = userRepository.findById(Long.parseLong(id)).orElseThrow(()->new UserNotFoundException(Long.parseLong(id)));
		user.setUserName(userDto.getUserName());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUpdatedBy(userDto.getUpdatedBy());
		user.setUpdatedDate(userDto.getUpdatedDate());
		userRepository.save(user);
		
		resultDto.setStatus(1);
		resultDto.setMessage("User updated");
		return resultDto;
	}

	@Override
	public User deleteUser(Long id) {
		
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return null;
	}

//	@Override
//	public UserDto add(UserDto userDto) {
//		
//		User user = new User();
//		
//		user.setName(userDto.getName());
//		user.setUserName(user.getUserName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		return userRepository.save(userDto);
//	}

}
