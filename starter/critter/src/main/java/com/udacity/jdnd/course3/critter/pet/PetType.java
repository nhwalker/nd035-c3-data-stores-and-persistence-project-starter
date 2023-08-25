package com.udacity.jdnd.course3.critter.pet;

/**
 * A example list of pet type metadata that could be included on a request to create a pet.
 */
public enum PetType {
    CAT, DOG, LIZARD, BIRD, FISH, SNAKE, OTHER;

    public static PetType toDTO(com.udacity.jdnd.course3.critter.entities.PetType entity){
        switch(entity){
            case CAT:
                return CAT;
            case DOG:
                return DOG;
            case LIZARD:
                return LIZARD;
            case BIRD:
                return BIRD;
            case FISH:
                return FISH;
            case SNAKE:
                return SNAKE;
            case OTHER:
            default:
                return OTHER;
        }
    }
    public static com.udacity.jdnd.course3.critter.entities.PetType fromDTO(PetType dto){
        switch(dto){
            case CAT:
                return com.udacity.jdnd.course3.critter.entities.PetType.CAT;
            case DOG:
                return com.udacity.jdnd.course3.critter.entities.PetType.DOG;
            case LIZARD:
                return com.udacity.jdnd.course3.critter.entities.PetType.LIZARD;
            case BIRD:
                return com.udacity.jdnd.course3.critter.entities.PetType.BIRD;
            case FISH:
                return com.udacity.jdnd.course3.critter.entities.PetType.FISH;
            case SNAKE:
                return com.udacity.jdnd.course3.critter.entities.PetType.SNAKE;
            case OTHER:
            default:
                return com.udacity.jdnd.course3.critter.entities.PetType.OTHER;
        }
    }
}
