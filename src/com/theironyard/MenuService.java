package com.theironyard;

import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/20/17.
 */
public class MenuService {
    // creating scanner variable from Scanner class
    private Scanner scanner;

    // MenuService uses scanner for user inputs
    public MenuService(Scanner scanner){
        this.scanner = scanner;
    }

    public static final int LIST_ANIMALS = 1;
    public static final int CREATE_ANIMAL = 2;
    public static final int VIEW_ANIMAL_DETAILS = 3;
    public static final int EDIT_ANIMAL = 4;
    public static final int DELETE_ANIMAL = 5;
    public static final int QUIT = 2;

    // Main Menu of Animal Shelter Program
    public int promptForMainMenuSelection(){
        System.out.println("\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");

        return waitForInt("Please choose an option:");
    }

    // Integer input from user
    public int waitForInt(String prompt){
        // display the prompt to the user
        System.out.println(prompt);
        // check if the next input is an int.
        if(!scanner.hasNextInt()){
            // if the next input is not an int, read it as a string to show in an error message
            String badInput = scanner.next();
            // show an error message
            System.out.printf("'%s' is not a valid number. Please try again.\n", badInput);
            // recursively prompt the user again
            return waitForInt(prompt);
        } else {
            // return the int the user provided
            return scanner.nextInt();
        }
    }


    // Prompts the user for animal properties, then creates and returns a new instance of the Animal class using that data.
    private Animal promptForAnimalData(String name, String species, String breed, String description){
        Animal animal = new Animal();
        animal.name = name;
        animal.species = species;
        animal.breed = breed;
        animal.description = description;
        return animal;
    }

    // Creating an animal
    private Animal promptForNewAnimal(String name, String species, String breed, String description){
        Animal animal = promptForAnimalData(name,species,breed,description);
        return animal;
    }

    private Animal promptForEditAnimal(String name, String species, String breed, String description){
        Animal animal = promptForAnimalData(name,species,breed,description);
        return animal;
    }

    // ***** ARRAY LIST OF ANIMALS ******* \\
}
