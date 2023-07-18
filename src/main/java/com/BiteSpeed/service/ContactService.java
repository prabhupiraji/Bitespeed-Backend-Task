package com.BiteSpeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BiteSpeed.entity.Contact;
import com.BiteSpeed.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

	@Autowired
    private  ContactRepository contactRepository;
     //creating primary contact
	  public Contact createPrimaryContact(String email, String phoneNumber) {
	        Contact contact = new Contact();
	        contact.setEmail(email);
	        contact.setPhoneNumber(phoneNumber);
	        contact.setLinkPrecedence("primary");
            contact.setCreatedAt(LocalDateTime.now());
//            contact.setDeletedAt(LocalDateTime.now());
            contact.setUpdatedAt(LocalDateTime.now());
	        return contactRepository.save(contact);
	    }

	    public Optional<Contact> findContactByEmail(String email) {
	        return contactRepository.findByEmail(email);
	    }
	  //creating secondary  contact
	    public Contact createSecondaryContact(Contact primaryContact, String phoneNumber,String email) {
	        Contact contact = new Contact();
	        contact.setEmail(email);
	        contact.setPhoneNumber(phoneNumber);
	        contact.setLinkedId(primaryContact.getId());
	        contact.setLinkPrecedence("secondary");
	        contact.setCreatedAt(LocalDateTime.now());
//            contact.setDeletedAt(LocalDateTime.now());
            contact.setUpdatedAt(LocalDateTime.now());
	        return contactRepository.save(contact);
	    }

		public Optional<Contact> findContactByPhoneNumber(String phoneNumber) {
			return contactRepository.findByPhoneNumber(phoneNumber);
		}

}

