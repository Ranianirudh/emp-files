package com.nextgen.emp.service;

import com.nextgen.emp.exception.EmployeeException;
import com.nextgen.emp.model.Employee;
import com.nextgen.emp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
    public Employee getEmployeeById(Long empId) {
        Optional<Employee> byId = employeeRepository.findById(empId);
        if (byId.isPresent()) {
            return byId.get();
        } else
            throw new EmployeeException("Employee not found");
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public List<Employee> writeEmployeesToFile(List<Employee> employees){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt"))) {
            writer.write("empId,name,age,salary,department");
            writer.newLine();
            for(Employee employee : employees){
                writer.write(employee.getEmpId() + "," + employee.getName() +"," + employee.getAge() +
                        "," + employee.getSalary()+ "," +employee.getDepartment());
                writer.newLine();
            }
            System.out.println("Employees written to file successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }

        return employees;
    }
   public List<Employee> readEmployeesFromFile(String filePath){
       List<Employee> employees = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; //skip first line
            while((line = (reader.readLine()))!=null) {
                    if(isFirstLine){
                        isFirstLine=false;
                        continue;
                    }
                if(StringUtils.hasText(line.trim())){
                    String[] split = line.split(",");
                    if(split.length==5) {
                        Employee employee = new Employee();
                        // StringUtils.hasText(split[0]) ? Long.valueOf() : -1;
                        long id = StringUtils.hasText(split[0]) ? Long.valueOf(split[0]) : -1;
                        employee.setEmpId(id);
                        employee.setName(split[1]);
                        int age = StringUtils.hasText(split[2]) ? Integer.valueOf(split[2]) : 0;
                        employee.setAge(age);
                        double salary = StringUtils.hasText(split[3]) ? Double.valueOf(split[3]) : 0.0;
                        employee.setSalary(salary);
                        employee.setDepartment(split[4]);
                        employees.add(employee);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }


}
