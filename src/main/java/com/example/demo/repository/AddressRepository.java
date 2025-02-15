package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {
     public List<Address> findByStateContaining(String state);
}
