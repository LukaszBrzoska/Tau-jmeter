package application.dao;

import application.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDAO {

    @Autowired
    EmployeeRepository employeeRepository;

    /* to save an employee */

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    /* search all employee */

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    /* get an employee */

    public Employee findOne(Long employeeId){
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        }else{
            throw new EntityNotFoundException("Can't find Employee by Id");
        }
    }

    /* delete an employee */

    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }

    /* delete an employee by Id */

    public void deleteById(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }
}
