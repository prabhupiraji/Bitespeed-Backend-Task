package com.BiteSpeed.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BiteSpeed.entity.Contact;
import com.BiteSpeed.entity.ContactRequestdto;
import com.BiteSpeed.service.ContactService;

@RestController
@RequestMapping("/identify")
public class ContactController {
	 @Autowired
    private ContactService contactService;

   
	 @PostMapping("/")
	    public ResponseEntity<String> createContact(@RequestBody ContactRequestdto request) {
	        String email = request.getEmail();
	        String phoneNumber = request.getPhoneNumber();

	        Optional<Contact> existingContactByEmail = contactService.findContactByEmail(email);
	        Optional<Contact> existingContactByPhoneNumber = contactService.findContactByPhoneNumber(phoneNumber);
	        if (existingContactByEmail.isPresent()&& existingContactByPhoneNumber.isPresent()) {
	            Contact primaryContact = existingContactByEmail.orElseGet(existingContactByPhoneNumber::get);
	            
	            if (!primaryContact.getPhoneNumber().equals(phoneNumber) || !primaryContact.getEmail().equals(email)) {
	                Contact secondaryContact = contactService.createSecondaryContact(primaryContact, phoneNumber,email);
	                String response = "Secondary contact created with ID: " + secondaryContact.getId();
	                return ResponseEntity.ok(response);     
	            }
	             
	        String response = "Contact already exists with ID: " + primaryContact.getId();
	        return ResponseEntity.ok(response);
	 }
	   if (existingContactByEmail.isPresent()) {
	        Contact primaryContact = existingContactByEmail.get();
	        if (!primaryContact.getPhoneNumber().equals(phoneNumber)) {
	            // Different phoneNumber found,Then create a new secondary contact
	            Contact secondaryContact = contactService.createSecondaryContact(primaryContact, phoneNumber, email);
	            String response = "Secondary contact created with ID: " + secondaryContact.getId();
	            return ResponseEntity.ok(response);
	        }
	        // Same phoneNumber found,Then return existing contact as primary
	        String response = "Contact already exists with ID: " + primaryContact.getId();
	        return ResponseEntity.ok(response);
	    }
	    if (existingContactByPhoneNumber.isPresent()) {
	        Contact primaryContact = existingContactByPhoneNumber.get();
	        if (!primaryContact.getEmail().equals(email)) {
	            // Different email found,Then create a new secondary contact
	            Contact secondaryContact = contactService.createSecondaryContact(primaryContact, phoneNumber, email);
	            String response = "Secondary contact created with ID: " + secondaryContact.getId();
	            return ResponseEntity.ok(response);
	        }
	        // Same email found, Then return existing contact as primary
	        String response = "Contact already exists with ID: " + primaryContact.getId();
	        return ResponseEntity.ok(response);
	    }
	  Contact primaryContact = contactService.createPrimaryContact(email, phoneNumber);
	  String response = "Primary contact created with ID: " + primaryContact.getId();
	  return ResponseEntity.ok(response);
}
}
