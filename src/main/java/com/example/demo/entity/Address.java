package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Address_tb")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;
    
    @Column(name = "state_name")
    private String state;
    
    @Column(name = "street_name")
    private String street;
    
    @Column(name = "pin_code")
    private long pinCode;
    
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Student> students;

    public Address() {
        super();
    }

    public Address(int id, String state, String street, long pinCode, List<Student> students) {
        super();
        this.id = id;
        this.state = state;
        this.street = street;
        this.pinCode=pinCode;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    	
    public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

	@Override
	public String toString() {
		return "Address [id=" + id + ", state=" + state + ", street=" + street + "]";
	}
    
}
