package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class PetService {

    private final PetRepository pets;
    private final CustomerRepository customers;

    public PetService(PetRepository pets, CustomerRepository customers){
        this.pets = pets;
        this.customers = customers;
    }

    public Pet savePet(@RequestBody Pet pet) {
        Pet saved= pets.save(pet);
        saved.getOwner().getPets().add(saved);
        return saved;
    }

    public Pet getPet(long petId) {
        return pets.findById(petId).orElseThrow(()-> new RuntimeException("No pet for id "+petId));
    }

    public List<Pet> getPets(){
        return pets.findAll();
    }
    public List<Pet> getPetsByOwner(long ownerId){
        return pets.findByOwnerId(ownerId);
    }
}
