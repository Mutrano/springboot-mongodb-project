package com.mutra.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutra.springbootmongodb.domain.User;
import com.mutra.springbootmongodb.dto.UserDTO;
import com.mutra.springbootmongodb.repository.UserRepository;
import com.mutra.springbootmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		List<User> users = repository.findAll();
		return users;
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(User obj) {
		Optional<User> newObj = repository.findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
		
	}
	
	public void updateData(Optional<User> newObj,User obj) {
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
