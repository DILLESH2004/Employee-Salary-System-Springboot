package com.example.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Validated @RequestBody Employee employee) {
        Employee saved = service.create(employee);
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Employee> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Employee e =  service.get(id);
        return ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") Long id, @Validated @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{role}")
    public List<Employee> byRole(@PathVariable("role") String role) {
        return service.byRole(role);
    }

    @GetMapping("/metrics/total-payroll")
    public Map<String, Double> totalPayroll() {
        Map<String, Double> result = new HashMap<>();
        result.put("totalPayroll", service.totalPayroll());
        return result;
    }
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<?> HandleEmployeeNotFoundException(EmployeeNotFound e){
        ErrorResponse error =  new ErrorResponse(LocalDateTime.now(),"Employee Not Found Exception","Employee not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
