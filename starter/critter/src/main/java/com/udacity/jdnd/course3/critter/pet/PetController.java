package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final CustomerService customerService;

    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.savePet(fromDTO(petDTO));
        return toDTO(pet);
    }



    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return toDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Pet fromDTO(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setPetType(PetType.fromDTO(petDTO.getType()));
        pet.setOwner(customerService.getCustomer(petDTO.getOwnerId()));

        return pet;
    }

    private PetDTO toDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setName(pet.getName());
        petDTO.setNotes(pet.getNotes());
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setType(PetType.toDTO(pet.getPetType()));
        return petDTO;
    }
}
