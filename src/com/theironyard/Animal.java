package com.theironyard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/20/17.
 */
public class Animal {
    private String name;
    private String species;
    private String breed;
    private String description;

    //test?

    Scanner scanner = new Scanner(System.in);

// MIGHT WANT TO SORT ALPHABETICALLY WHEN THERE ARE ACTUAL ANIMALS
//             ArrayList<Animal> listOfAnimals = animal.listOfAnimals.sort();
//                Collections.sort(animal.listOfAnimals, new Comparator() {
//                    @Override
//                    public int compare(Object o1, Object o2) {
//                        return 0;
//                    }
//
//                    @Override
//                    public boolean equals(Object obj) {
//                        return false;
//                    }
//                }){
//
//                }


    // Getters and Setters for each property
    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()){
            this.name = name;
            System.out.println("The name of the animal is: " + this.name);
        }else {
            System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                    "The name for the animal is required! Please try again.\n"
                     + MenuService.ANSI_RESET);
            setName();
        }
    }


    // species
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setSpecies(){
        String species = scanner.nextLine();
        if (!species.trim().isEmpty()){
            this.species = species;
            System.out.println("The species of the animal is: " + this.species);
        } else {
            System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                    "The species for the animal is required! Please try again.\n"
                    + MenuService.ANSI_RESET);
            setSpecies();;
        }
    }


    // breed
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBreed(){
        String breed = "";
        breed = scanner.nextLine();
        this.breed = breed;
        System.out.println("The breed of the animal is: " + this.breed);
    }


    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription(){
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()){
            this.description = description;
            System.out.println("The description of the animal has been created.");
        } else {
            System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                    "The description for the animal is required! Please try again.\n"
                    + MenuService.ANSI_RESET);
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name:'" + name + '\'' +
                ", species:'" + species + '\'' +
                ", breed:'" + breed + '\'' +
                ", description:'" + description + '\'' +
                '}';
    }
}
