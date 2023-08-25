package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>  {
    List<Schedule> findAllByPetsId(long petId);

    List<Schedule> findAllByEmployeesId(long employeeId);

    List<Schedule> findAllByPets_OwnerId(long customerId);
}
