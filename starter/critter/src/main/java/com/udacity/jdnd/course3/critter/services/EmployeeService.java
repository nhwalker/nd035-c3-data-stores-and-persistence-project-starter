package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.JobTask;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employeeDTO) {
        return employeeRepository.save(employeeDTO);
    }


    public Employee getEmployee( long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("No employee for id "+employeeId));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }


    public List<Employee> findEmployeesForService(Set<JobTask> tasks, LocalDate date) {
        return employeeRepository.findBySupportedTasksInAndDaysAvailableContaining(tasks,date.getDayOfWeek())
                .stream().filter(x->x.getSupportedTasks().containsAll(tasks)).distinct().collect(Collectors.toList());
    }
}
