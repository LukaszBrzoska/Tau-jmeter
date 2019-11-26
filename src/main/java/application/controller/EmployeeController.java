package application.controller;

import application.dao.EmployeeDAO;
import application.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeDAO employeeDAO;

    /* to save an employee */
    @PostMapping(path = "/save", produces = "application/json")
    public Employee createEmployee(@Valid @RequestBody Employee emp) {
        return employeeDAO.save(emp);
    }

    /* to get all employee */
    @GetMapping(path = "/getAll", produces = "application/json")
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    /* get an employee by Id */
    @GetMapping(path = "/getById/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long empId) {

        Employee emp = employeeDAO.findOne(empId);

        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(emp);
    }

    /* update an employee by Id */
    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empId, @Valid @RequestBody Employee empDetail) {

        Employee emp = employeeDAO.findOne(empId);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        emp.setName(empDetail.getName());
        emp.setDesignation(empDetail.getDesignation());
        emp.setExpertise(empDetail.getExpertise());

        Employee updateEmployee = employeeDAO.save(emp);
        return ResponseEntity.ok().body(updateEmployee);
    }

    /* delete Employee by Id */
    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long empId) {

        Employee emp = employeeDAO.findOne(empId);
        if (empId == null) {
            return ResponseEntity.notFound().build();
        }
        employeeDAO.delete(emp);
        return ResponseEntity.ok().build();
    }
}
