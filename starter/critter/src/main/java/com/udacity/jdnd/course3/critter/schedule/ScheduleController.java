package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;
    private final PetService petService;
    private final EmployeeService employeeService;

    public ScheduleController(ScheduleService service, PetService petService, EmployeeService employeeService) {
        this.service = service;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return toDTO(service.createSchedule(fromDTO(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules=service.getAllSchedules();
        return schedules.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return service.getScheduleForPet(petId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return service.getScheduleForEmployee(employeeId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return service.getScheduleForCustomer(customerId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ScheduleDTO toDTO(Schedule schedule){
        ScheduleDTO dto=new ScheduleDTO();
        dto.setDate(schedule.getDate());
        dto.setId(schedule.getId());
        dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        dto.setActivities(schedule.getTasks().stream().map(EmployeeSkill::toDTO).collect(Collectors.toSet()));
        return dto;
    }
    public Schedule fromDTO(ScheduleDTO dto){
        Schedule schedule=new Schedule();
        schedule.setDate(dto.getDate());
        schedule.setId(dto.getId());
        schedule.setEmployees(dto.getEmployeeIds().stream().map(this::toEmployee).collect(Collectors.toSet()));
        schedule.setPets(dto.getPetIds().stream().map(this::toPet).collect(Collectors.toList()));
        schedule.setTasks(dto.getActivities().stream().map(EmployeeSkill::fromDTO).collect(Collectors.toSet()));
        return schedule;
    }
    private Employee toEmployee(long employeeId){
        return employeeService.getEmployee(employeeId);
    }
    private Pet toPet(long petId){
        return petService.getPet(petId);
    }
}
