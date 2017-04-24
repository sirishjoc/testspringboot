package com.testspringboot.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testspringboot.api.MedicineManager;
import com.testspringboot.domain.Medicine;
import com.testspringboot.repository.MedicineRepository;

@Service
public class MedicineManagerImpl implements MedicineManager{

	@Autowired
	private MedicineRepository medicineRepository;
	
	@Override
	public Medicine addNew(Medicine t) {
		return this.medicineRepository.save(t);
	}

	@Override
	public void addAll(List<Medicine> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medicine update(Medicine t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Medicine t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBy(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medicine find(String id) {
		return this.medicineRepository.findOne(id);
	}

	@Override
	public List<Medicine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findWithPaging(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countRecord() {
		// TODO Auto-generated method stub
		return null;
	}

}
