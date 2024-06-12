package com.example.demo.Dto;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class StudentDto {
	private int id;

	@NotEmpty(message = "Name cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\\\s-]+$", message = "Enter correct Name")
	private String name;

	@NotEmpty(message = "Designation cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\\\s-]+$", message = "Enter correct String value")
	private String designation;

	@NotEmpty(message = "Date of birth cannot be empty")
	@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "Enter Date in 'YYYY-MM-DD'")
	private LocalDate dateOfBirth;

	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "^\\d{10}$", message = "Enter 10 digit mobile number")
	private String mobileNumber;

	private int addressId;

	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDto(int id, String name, String designation, LocalDate dateOfBirth, String mobileNumber,
			int addressId) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.addressId = addressId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", designation=" + designation + ", dateOfBirth="
				+ dateOfBirth + ", mobileNumber=" + mobileNumber + ", addressId=" + addressId + "]";
	}

}