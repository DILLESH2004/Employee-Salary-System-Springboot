package com.example.springjdbc;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee e) {
        return repository.save(e);
    }

    public Employee update(Long id, Employee e) {
           Employee existing = repository.findById(id)
                   .orElseThrow(() -> new EmployeeNotFound("Employee not found: " + id));
           existing.setName(e.getName());
           existing.setRole(e.getRole());
           existing.setSalary(e.getSalary());
           existing.setHireDate(e.getHireDate());
           return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Employee get(Long id) {
      return repository.findById(id)
              .orElseThrow(()->new EmployeeNotFound("Employee not found : "+id));
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public List<Employee> byRole(String role) {
        return repository.findByRoleIgnoreCase(role);
    }

    public double totalPayroll() {
        return repository.findAll().stream()
                .mapToDouble(e -> e.getSalary() == null ? 0.0 : e.getSalary())
                .sum();
    }


}
