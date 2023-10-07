package com.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, Long> {

	@Transactional
	void deleteByObjectId(ObjectId objectId);

	@Transactional
	Optional<UserModel> findByObjectId(ObjectId objectId);
}
