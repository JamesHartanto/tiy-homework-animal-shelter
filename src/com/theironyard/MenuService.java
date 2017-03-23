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

    // Useful constants for main menu
    public static final int LIST_ANIMALS = 1;
    public static final int CREATE_ANIMAL = 2;
    public static final int VIEW_ANIMAL_DETAILS = 3;
    public static final int EDIT_ANIMAL = 4;
    public static final int DELETE_ANIMAL = 5;
    public static final int QUIT = 6;
    //Color constants
        // Text color
        // Red \u001B31;1m
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
        // Background colo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
            // Ex: System.out.println(ANSI_GREEN_BACKGROUND + ANSI_RED + "This text has a green background and red text!" + ANSI_RESET");


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
        if(!scanner.hasNextInt() || scanner.nextInt() > 6 || scanner.nextInt() < 1){
            // if the next input is not an int, read it as a string to show in an error message
            String badInput = scanner.nextLine();
            // show an error message
            System.out.println(ANSI_GREEN_BACKGROUND + ANSI_RED + "Sorry, that is not a valid option. Please try again.\n" + ANSI_RESET);
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
    public Animal promptForNewAnimal(String name, String species, String breed, String description){
        Animal animal = promptForAnimalData(name,species,breed,description);
        return animal;
    }

    public Animal promptForEditAnimal(String name, String species, String breed, String description){
        Animal animal = promptForAnimalData(name,species,breed,description);
        return animal;
    }

    // ***** ARRAY LIST OF ANIMALS ******* \\
}
