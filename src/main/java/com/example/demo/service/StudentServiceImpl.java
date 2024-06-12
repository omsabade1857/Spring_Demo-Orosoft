package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AddressDto;
import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
//import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.EmptyResultException;
import com.example.demo.exception.ResourseNotFound;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public StudentDto getStudentById(int id){
		Optional<Student> option = studentRepository.findById(id);
				if(option.isPresent()) {
					return modelMapper.map(option, StudentDto.class);
				}else {
					 throw new ResourseNotFound("Student with this id not found :"+id);
				}
	}


	@Override
	public List<StudentDto> getStudentByName(String name) {
		List<Student> student=studentRepository.findByNameContainingIgnoreCase(name);
		if(student!=null && !student.isEmpty()) {
			return student.stream()
					.map(s->modelMapper.map(s, StudentDto.class)).toList();
		}
			throw new EmptyResultException("Student with this name not found :"+ name);
		
	}



	@Override
	public List<StudentDto> getStudentByDesignation(String designation) {
		List<Student> designation2 = studentRepository.findByDesignationIgnoreCase(designation);
		if(designation2==null && designation2.isEmpty()) {
			throw new ResourseNotFound("Student with this role not found :"+ designation);
		}
		return designation2.stream().map(s->modelMapper.map(s, StudentDto.class)).toList();
	}


	@Override
	public List<StudentDto> getAllStudent() {
		List<Student> students=studentRepository.findAll();
		if(students!=null) {
			return students.stream().map(s->modelMapper.map(s, StudentDto.class)).toList();
		}
		throw new ResourseNotFound("Students not found :");
	}
	
}