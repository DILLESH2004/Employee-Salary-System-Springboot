package com.example.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
            .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
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
            .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
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
