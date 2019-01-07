package com.facingahead.app;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsersRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
}