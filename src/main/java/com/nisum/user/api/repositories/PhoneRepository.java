package com.nisum.user.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.user.api.model.entity.Phone;
import com.nisum.user.api.model.entity.User;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, String>{
	
}


