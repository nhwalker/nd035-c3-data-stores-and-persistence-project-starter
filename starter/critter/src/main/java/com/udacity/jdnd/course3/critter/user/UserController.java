package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final PetService petService;

    public UserController(EmployeeService employeeService, CustomerService customerService, PetService petService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.petService = petService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return toDTO(customerService.saveCustomer(fromDTO(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        System.out.println(customer);
        return toDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return toDTO(employeeService.saveEmployee(fromDTO(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return toDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.findEmployeesForService(employeeDTO.getSkills().stream().map(EmployeeSkill::fromDTO)
                        .collect(Collectors.toSet()),employeeDTO.getDate())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private EmployeeDTO toDTO(Employee employee){
        EmployeeDTO dto=new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDaysAvailable(employee.getDaysAvailable());
        dto.setSkills(employee.getSupportedTasks().stream().map(EmployeeSkill::toDTO).collect(Collectors.toSet()));
        return dto;
    }

    private Employee fromDTO(EmployeeDTO dto){
        Employee employee=new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setDaysAvailable(dto.getDaysAvailable());
        employee.setSupportedTasks(dto.getSkills().stream().map(EmployeeSkill::fromDTO).collect(Collectors.toSet()));
        return employee;
    }

    public CustomerDTO toDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setNotes(customer.getNotes());
        if(customer.getPets()!=null) {
            dto.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }

    public Customer fromDTO(CustomerDTO dto){
        Customer customer=new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setNotes(dto.getNotes());
        if(dto.getPetIds()!=null) {
            customer.setPets(dto.getPetIds().stream().map(this::fromPetId).collect(Collectors.toSet()));
        }
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }

    private Pet fromPetId(Long id){
        return petService.getPet(id);
    }
}
