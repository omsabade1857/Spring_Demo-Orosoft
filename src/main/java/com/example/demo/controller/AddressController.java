package com.example.demo.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AddressDto;
import com.example.demo.service.AddressService;

import jakarta.validation.Valid;

@RestController
public class AddressController {
	 private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@PostMapping("/new")
	public ResponseEntity<AddressDto> createStudent(@Valid @RequestBody AddressDto addressDto) {
		logger.debug("Received request to create address: {}", addressDto);
		AddressDto addressDto2 = addressService.createAddress(addressDto);
		 logger.debug("Created address: {}", addressDto2);
		return new ResponseEntity<>(addressDto2, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> getStudent(@PathVariable("id") int id) {
		AddressDto addressDto = addressService.getAddressById(id);
		
		if(addressDto!=null) {
			logger.debug("Found address: {}",addressDto);
			return ResponseEntity.ok(addressDto);
		}else {
			logger.warn("Not found address: {}",addressDto);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@GetMapping("/state/{state}")
	public ResponseEntity<List<AddressDto>> getAddressByState(@PathVariable("state") String state){
		logger.debug("Received request to get by State Name ");
		List<AddressDto> addressDto =addressService.getAddressByState(state);
		
		if(addressDto != null && !addressDto.isEmpty()) {
			logger.debug("Employee fetch sucessfully {} ",addressDto);
			return new ResponseEntity<>(addressDto,HttpStatus.OK);
		}
			logger.warn("Data is empty");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	@GetMapping("/all")
	public ResponseEntity<List<AddressDto>> getStudent() {
		 logger.debug("Received request to get all addresses");
		List<AddressDto> addressDto = addressService.getAllAddress();
		if(addressDto!=null) {
			logger.debug("Employee fetch sucessfully {} ",addressDto);
			return new ResponseEntity<>(addressDto,HttpStatus.ACCEPTED);
		}else {
			logger.warn("Data is empty");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AddressDto> updateStudent(@PathVariable("id") int id,@Valid @RequestBody AddressDto addressDto){
		 logger.debug("Received request to update address with id: {}", id);
		AddressDto addressDto2=addressService.updateAddressById(id, addressDto);
		 if (addressDto2 != null) {
	            logger.debug("Updated address: {}", addressDto2);
	            return new ResponseEntity<>(addressDto2, HttpStatus.OK);
	        } else {
	            logger.warn("Address with id {} not found for update", id);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
		logger.debug("Delete Request: ");
		if(addressService.getAddressById(id)!=null) {
			logger.warn("Your data deleted by id {}",id);
			addressService.deleteAddressById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Data Deleted !!");
		}
		logger.warn("Address not found by id {}",id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with id not found !!");
	}
	
	
}
