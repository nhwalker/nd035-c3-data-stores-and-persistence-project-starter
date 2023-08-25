package com.udacity.jdnd.course3.critter.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne //many pets can belong to one customer
    @JoinColumn(name="owner_id")
    private Customer owner;

    @Nationalized
    @Column(length = 75)
    private String name;

    private LocalDate birthDate;

    @Nationalized
    @Column(length = 2000)
    private String notes;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "pets")
    private Set<Schedule> scheduled;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Schedule> getScheduled() {
        return scheduled;
    }

    public void setScheduled(Set<Schedule> scheduled) {
        this.scheduled = scheduled;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", owner=" + (owner==null?"null":owner.getId()) +
                ", name='" + name + '\'' +
                ", petType='" + petType + '\'' +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                ", scheduled=" + (scheduled==null?"null":scheduled.stream().map(Schedule::getId).collect(Collectors.toList())) +
                '}';
    }
}
