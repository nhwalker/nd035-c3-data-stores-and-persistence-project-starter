package com.udacity.jdnd.course3.critter.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee{

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    @Column(length = 75)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    private Set<Schedule> scheduled;

    @ElementCollection(targetClass=JobTask.class)
    @CollectionTable(name="employees_supported_tasks", joinColumns = @JoinColumn(name="employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<JobTask> supportedTasks;

    @ElementCollection(targetClass=DayOfWeek.class)
    @CollectionTable(name="employee_availability", joinColumns = @JoinColumn(name="employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JobTask> getSupportedTasks() {
        return supportedTasks;
    }

    public void setSupportedTasks(Set<JobTask> supportedTasks) {
        this.supportedTasks = supportedTasks;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Set<Schedule> getScheduled() {
        return scheduled;
    }

    public void setScheduled(Set<Schedule> scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scheduled=" + scheduled +
                ", supportedTasks=" + supportedTasks +
                ", daysAvailable=" + daysAvailable +
                '}';
    }
}
