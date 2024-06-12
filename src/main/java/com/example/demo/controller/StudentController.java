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
import com.example.demo.Dto.StudentDto;
import com.example.demo.service.AddressService;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@GetMapping("/student/{id}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("id") int id) {
		StudentDto studentDto = studentService.getStudentById(id);

		if (studentDto != null) {
			logger.debug("Found Student: {}", studentDto);
			return ResponseEntity.ok(studentDto);
		} else {
			logger.warn("Not found student: {}", studentDto);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/student/name/{name}")
	public ResponseEntity<List<StudentDto>> getStudentByName(@PathVariable("name") String name) {
		logger.debug("Received request to get by Name ");
		List<StudentDto> studentDtos = studentService.getStudentByName(name);

		if (studentDtos != null && !studentDtos.isEmpty()) {
			logger.debug("Employees fetched successfully: {}", studentDtos);
			return new ResponseEntity<>(studentDtos, HttpStatus.OK);
		} else {
			logger.warn("Data is empty");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/student/role/{name}")
	public ResponseEntity<List<StudentDto>> getStudentByDesignation(@PathVariable("name") String designation) {
		logger.debug("Received request to get by Designation ");
		List<StudentDto> studentDtos = studentService.getStudentByDesignation(designation);

		if (studentDtos != null && !studentDtos.isEmpty()) {
			logger.debug("Employees fetched successfully: {}", studentDtos);
			return new ResponseEntity<>(studentDtos, HttpStatus.OK);
		} else {
			logger.warn("Data is empty");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/student/all")
	public ResponseEntity<List<StudentDto>> getStudent() {
		logger.debug("Received request to get all addresses");
		List<StudentDto> studentDtos = studentService.getAllStudent();
		if (studentDtos != null) {
			logger.debug("Employee fetch sucessfully {} ", studentDtos);
			return new ResponseEntity<>(studentDtos, HttpStatus.ACCEPTED);
		} else {
			logger.warn("Data is empty");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
