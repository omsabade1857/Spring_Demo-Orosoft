package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Student_tb")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;
    
    @Column(name = "student_name")
    private String name;
    
    @Column(name = "designation")
    private String designation;
    
//    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "date_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "mobile_no")
    private String mobileNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;
    
    public Student() {
        super();
    }

    public Student(int id, String name, Address address, String designation, LocalDate dateOfBirth, String mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.designation = designation;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
		return "Student [id=" + id + ", name=" + name + ", designation=" + designation + ", dateOfBirth=" + dateOfBirth
				+ ", mobileNumber=" + mobileNumber + "]";
	}

	
}
