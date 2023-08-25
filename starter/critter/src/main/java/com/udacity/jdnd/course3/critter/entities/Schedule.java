package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(targetClass=JobTask.class)
    @CollectionTable(name="scheduled_tasks", joinColumns = @JoinColumn(name="schedule_id"))
    @Enumerated(EnumType.STRING)
    private Set<JobTask> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<JobTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<JobTask> tasks) {
        this.tasks = tasks;
    }
}
