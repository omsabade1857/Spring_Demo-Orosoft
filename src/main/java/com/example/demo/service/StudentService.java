package com.example.demo.service;

import java.util.List;

import com.example.demo.Dto.AddressDto;
import com.example.demo.Dto.StudentDto;

public interface StudentService {
	
	public StudentDto getStudentById(int id);
	
	//find student by name
	public List<StudentDto> getStudentByName(String name);
	
	//find student by name
	public List<StudentDto> getStudentByDesignation(String designation);
	
	//find all student
	public List<StudentDto> getAllStudent();
	
	
	
//	public AddressDto updateAddressById(int id, AddressDto addressDto);
//	public void deleteAddressById(int id);
}
