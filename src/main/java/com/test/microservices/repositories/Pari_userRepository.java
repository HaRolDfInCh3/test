package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Pari_user;

public interface Pari_userRepository extends MongoRepository<Pari_user, String> {
	public Pari_user findById(int id2);
	public Boolean existsById(int id2);
	public Pari_user findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Pari_user deleteById(int id2);
}
