package com.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.model.UserModel;
import com.repository.UserRepository;

@Service
public class UserServiceImpl{

	@Autowired
	private UserRepository userRepository;

	public List<UserModel> getAllUsers(){
		List<UserModel> data = userRepository.findAll();
		return data;
	}

	public void addUser(UserModel request) {
		//request.setId(UserServiceImpl.getSequenceNumber(SEQUENCE));
		System.out.println(request);
		UserModel data = userRepository.save(request);
		System.out.println(data);		
	}

	public void deleteUser(ObjectId id) {
		System.out.println(id);
		userRepository.deleteByObjectId(id);
	}

	public Optional<UserModel> getUserById(ObjectId id1) {
		System.out.println(id1);
		Optional<UserModel> id = userRepository.findByObjectId(id1);
		return id;
	}

	public void updateUser(UserModel request) {
		
		UserModel user = new UserModel();
		user.setObjectId(request.getObjectId());
		user.setName(request.getName());
		user.setMobile(request.getMobile());
		user.setAddress(request.getAddress());
		user.setAge(request.getAge());
		System.out.println(request);
		UserModel data = userRepository.save(user);
		System.out.println(data);
	}
}
