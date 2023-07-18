package com.BiteSpeed.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BiteSpeed.entity.Contact;
//import com.BiteSpeed.service.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

//	 List<Contact> findByPhoneNumberOrEmail(String phoneNumber, String email);

	Optional<Contact> findByEmail(String email);

	Optional<Contact> findByPhoneNumber(String phoneNumber);

}
