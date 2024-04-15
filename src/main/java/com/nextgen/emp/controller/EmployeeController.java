package com.nextgen.emp.controller;

import com.nextgen.emp.model.Employee;
import com.nextgen.emp.service.EmployeeService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.BitSet;
import java.util.List;

@RestController
@RequestMapping("/api/emp")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody @NotNull Employee employee) {
        Employee emp = employeeService.createEmployee(employee);
        return ResponseEntity.ok(emp);
    }
    //1.Create User defined exception throw it when Emp not found
    @GetMapping("/id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId){
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }
    //5.you have List<Employee> objects write to file with header
    @PostMapping("/writeToFile/")
    public ResponseEntity<?> writeEmployeesToFile(@RequestBody @NotNull List<Employee> employees) {
        List<Employee> employeesList = employeeService.writeEmployeesToFile(employees);
        return ResponseEntity.ok(employeesList);
    }
    //4.a file has data with Header and Data
    //   Name, Age, Sal
    //   Ram, 27,10000
    //…..
    //
    //   Read this file store it into List<Employee> objects. Handle Empty rows effectively
    @GetMapping("/read-employee-from-File/")
    public ResponseEntity<?> readEmployeesFromFile(){
            List<Employee> employees = employeeService.readEmployeesFromFile("employee.txt");
            return ResponseEntity.ok(employees);
    }
}
