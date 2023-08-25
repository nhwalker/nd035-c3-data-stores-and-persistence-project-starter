package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entities.JobTask;

/**
 * A example list of employee skills that could be included on an employee or a schedule request.
 */
public enum EmployeeSkill {
    PETTING, WALKING, FEEDING, MEDICATING, SHAVING;

    public static EmployeeSkill toDTO(JobTask task){
        switch(task){
            case PETTING:
                return EmployeeSkill.PETTING;
            case WALKING:
                return EmployeeSkill.WALKING;
            case FEEDING:
                return EmployeeSkill.FEEDING;
            case MEDICATING:
                return EmployeeSkill.MEDICATING;
            case SHAVING:
                return EmployeeSkill.SHAVING;
            default:
                throw new IllegalArgumentException("Unsupported task "+task);
        }}

    public static JobTask fromDTO(EmployeeSkill skill){
        switch(skill){
            case PETTING:
                return JobTask.PETTING;
            case WALKING:
                return JobTask.WALKING;
            case FEEDING:
                return JobTask.FEEDING;
            case MEDICATING:
                return JobTask.MEDICATING;
            case SHAVING:
                return JobTask.SHAVING;
            default:
                throw new IllegalArgumentException("Unsupported skill "+skill);
        }
    }
}
