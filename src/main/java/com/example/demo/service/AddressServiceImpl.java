package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AddressDto;
import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.repository.AddressRepository;
//import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.EmptyResultException;
import com.example.demo.exception.ResourseNotFound;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
//	
//	@Autowired
//	private StudentRepository studentRepository;


	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AddressDto createAddress(AddressDto addressDto) {

	Address address = modelMapper.map(addressDto, Address.class);
	if(address==null || address.getStudents()==null) {
		throw new NullPointerException("Address is Empty!! please fill correct data ");
	}
		//save the student data
	 List<Student> students = addressDto.getStudents().stream()
             .map(studentDto -> {
                 Student student = modelMapper.map(studentDto, Student.class);
                 student.setAddress(address);
                 return student;
             })
             .collect(Collectors.toList());
	 
	 //to check student data
	 if(students==null) {
		 throw new NullPointerException("Please enter student data!!");
	 }
	 
	 for (Student student : address.getStudents()) {
		 System.out.println("Student to be saved: " + student.getName());
	 }
	 
	 
     address.setStudents(students);

     Address savedAddress = addressRepository.save(address);
     return modelMapper.map(savedAddress, AddressDto.class);
	}

	@Override
	public AddressDto getAddressById(int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFound("Address not found with id " + id));

		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public List<AddressDto> getAddressByState(String state) {
		List<Address> address=addressRepository.findByStateContaining(state);
		if(address != null && !address.isEmpty()) {
			return address.stream()
					.map(s-> modelMapper.map(s, AddressDto.class)).toList();
		}
		throw new ResourseNotFound("Address not found with state name :"+ state);
	}
	
	
	@Override
	public List<AddressDto> getAllAddress() {
		List<Address> addresses=addressRepository.findAll();
		if(addresses.isEmpty())
			throw new ResourseNotFound("List Empty :");
		
		return addresses.stream()
				.map(s->modelMapper.map(s,AddressDto.class)).toList();
	}

	
	/*  @Override
	public AddressDto updateAddressById(int id, AddressDto addressDto) {
		Address address=addressRepository.findById(id).orElseThrow(()-> new ResourseNotFound("Address not found with id " + id));
		address.setState(addressDto.getState());
		address.setStreet(addressDto.getStreet());
		
					//		{
			//            Student student = modelMapper.map(studentDto, Student.class);
			//            student.setAddress(address); // Set the address for each student
			//            return student;
			//        }
				
//		address.setStudent(addressDto.getStudents());  //not perfect way
		
		System.out.println("request" + addressDto.getStudents());
	//	address.getStudents().clear();  //clear existing student data
		System.out.println("responce" + address.getStudents());
		
		List<Student> students=addressDto.getStudents().stream()
				.map(studentDto->{ 
					Student student= modelMapper.map(studentDto, Student.class);
					System.out.println(student.getName()+" Hello !!!");
//				List<Student> student2=address.getStudents();
				
					student.setAddress(address);
					return student;
		}).toList();
		
	//	address.setStudents(students);
		Address address2=addressRepository.save(address);
		return modelMapper.map(address2, AddressDto.class);
		
		
		
	}   */
	
	  @Override
	public AddressDto updateAddressById(int id, AddressDto addressDto) {
		Address address=addressRepository.findById(id).orElseThrow(()-> new ResourseNotFound("Address not found with id " + id));
		address.setState(addressDto.getState());
		address.setStreet(addressDto.getStreet());
		address.setPinCode(addressDto.getPinCode());
		
					//		{
			//            Student student = modelMapper.map(studentDto, Student.class);
			//            student.setAddress(address); // Set the address for each student
			//            return student;
			//        }
				
//		address.setStudent(addressDto.getStudents());  //not perfect way
		
		System.out.println("request" + addressDto.getStudents());
	//	address.getStudents().clear();  //clear existing student data
		System.out.println("responce" + address.getStudents());
		
		List<Student> students=addressDto.getStudents().stream()
				.map(studentDto->{ 
					Student student= modelMapper.map(studentDto, Student.class);
					System.out.println(student.getName()+" Hello !!!");
//				List<Student> student2=address.getStudents();
					
//					List<Student> student1 =address.getStudents();
//					student1.set(student.getId(), student);
					
					student.setAddress(address);
					return student;
		}).toList();
		
		 // Update existing students or add new ones....I take help from chatGpt here
	    for (Student newStudent : students) {
	        boolean studentExists = false;
	        for (Student existingStudent : address.getStudents()) {
	            if (existingStudent.getId() == newStudent.getId()) {
	                existingStudent.setName(newStudent.getName());
	                existingStudent.setDesignation(newStudent.getDesignation());
	                existingStudent.setDateOfBirth(newStudent.getDateOfBirth());
	                existingStudent.setMobileNumber(newStudent.getMobileNumber());
	                studentExists = true;
	                break;
	            }
	        }
	        if (!studentExists) {
	        	System.out.println("New student created: "+newStudent.getName());
	            address.getStudents().add(newStudent);
	        }
	    }

	    // Remove students that are not in the new list
	    address.getStudents().removeIf(existingStudent -> 
	            students.stream().noneMatch(newStudent -> newStudent.getId() == existingStudent.getId())
	    );
		
//		address.getStudents().addAll(students);
		
//		 List<Student> savedStudents =studentRepository.saveAll(students);
//		
//		address.setStudents(savedStudents);
		Address address2=addressRepository.save(address);
		return modelMapper.map(address2, AddressDto.class);
		
	}
	

	@Override
	public void deleteAddressById(int id) {
		if(addressRepository.findById(id)!=null) {
			addressRepository.deleteById(id);
		}else {
			throw new ResourseNotFound("Address not found with id " + id);
		}
	}

	
	
}