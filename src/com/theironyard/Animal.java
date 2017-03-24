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

    ArrayList<Animal> listOfAnimals = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // Getters and Setters for each property

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {
        String name = "";
        if (!scanner.nextLine().isEmpty()){
            name = scanner.nextLine();
            this.name = name;
            System.out.println("The name of the animal is: " + this.name);
        }else {
            System.out.println("The name for the animal is required! Please try again.");
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
        String species = "";
        if (!scanner.nextLine().isEmpty()){
            species = scanner.nextLine();
            this.species = species;
            System.out.println("The species of the animal is: " + this.species);
        } else {
            System.out.println("The species for the animal is required! Please try again.");
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
        if (!scanner.nextLine().isEmpty()){
            breed = scanner.nextLine();
            this.breed = breed;
            System.out.println("The breed of the animal is: " + this.breed);
        } else {
            System.out.println("The breed for the animal is required! Please try again.");
            setBreed();
        }
    }


    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription(){
        String description = "";
        if (!scanner.nextLine().isEmpty()){
            description = scanner.nextLine();
            this.description = description;
            System.out.println("The description of the animal has been created.");
        } else {
            System.out.println("The description for the animal is required! Please try again.");
        }

    }
}
