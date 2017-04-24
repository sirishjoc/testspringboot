package com.testspringboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.testspringboot.domain.Medicine;

public interface MedicineRepository extends MongoRepository<Medicine, String>{
	
}
