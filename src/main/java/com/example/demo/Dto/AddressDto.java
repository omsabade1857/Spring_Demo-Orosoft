package com.example.demo.Dto;

import java.util.List;

import com.example.demo.entity.Student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AddressDto {
    private int id;

    @NotEmpty(message = "State cannot be empty")
    @Pattern(regexp = "^[A-Za-z\\s-]+$", message = "Enter only alphabets, spaces, and '-' are allowed")
    private String state;

    @NotEmpty(message = "Street cannot be empty")
    @Pattern(regexp = "^[A-Za-z\\s-]+$", message = "Enter only alphabets, spaces, and '-' are allowed")
    private String street;

//    @NotEmpty(message = "Pincode cannot be empty")
  // @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers")//....not work in case of Number!!
   // @Numeric(message = "Enter only numbers")
   private long pinCode;
    

    private List<StudentDto> students;

    public AddressDto() {
        super();
    }

    public AddressDto(int id, String state, String street, long pinCode, List<StudentDto> students) {
        super();
        this.id = id;
        this.state = state;
        this.street = street;
        this.pinCode = pinCode;
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

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "AddressDto [id=" + id + ", state=" + state + ", street=" + street + ", pinCode=" + pinCode + ", students=" + students + "]";
    }
}
