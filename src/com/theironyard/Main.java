package com.theironyard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/20/17.
 */
public class Main {

    public static void main(String[] args) {
        // Arraylist to hold the animals
        ArrayList<Animal> listOfAnimals = new ArrayList<>();

        // Creating instances to use classes
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService(scanner);
        Animal animal = new Animal();

        while(true) {
            int action = menuService.promptForMainMenuSelection();

            if(action == MenuService.LIST_ANIMALS){
                for (int x = 0; x < listOfAnimals.size(); x = x + 1) {
                    // Not sure how to use formats yet
                    // String format1 = "%-5s%s%n";
                    // String format1 = "%2d. %-20s $%.2f%n";
                    System.out.printf("%s)   %s   %s\n",
                            (x+1),
                            listOfAnimals.get(x).getName(),
                            listOfAnimals.get(x).getSpecies());
                }


            } else if(action == MenuService.CREATE_ANIMAL){
                // Prompting user to insert information about new animal
                System.out.println("--- Create an Animal ---");

                // Animal name
                System.out.println("What is the animal name?");
                animal.setName();

                // Animal species
                System.out.println("What is the species?");
                animal.setSpecies();

                // Animal breed
                System.out.println("What is the breed? (optional)");
                animal.setBreed();

                // Animal description
                System.out.println("Please describe the animal!");
                animal.setDescription();

                // Creating new animal
                Animal newAnimal = menuService.promptForNewAnimal(animal.getName(),
                        animal.getSpecies(),animal.getBreed(),animal.getDescription());
                // Adding animal to the list
                listOfAnimals.add(0,newAnimal);
                System.out.println("Animal has been created!");


                // Viewing animal details
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS){
                // Asks user for which animal to see
                System.out.println("What is the numeric ID of the animal you want to view?");
                int x = 0;
                // check if the next input is an int.
                String choice = scanner.nextLine();
                // while loop to check input is valid
                while (MenuService.isInteger(choice) == false || Integer.parseInt(choice) > listOfAnimals.size() ||
                        Integer.parseInt(choice) < 1) {
                    System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                            "Sorry, that is not a valid option. Please try again.\n" + MenuService.ANSI_RESET);
                    choice = scanner.nextLine();
                }
                // return the int the user provided
                x = Integer.parseInt(choice)-1;
                System.out.println(listOfAnimals.get(x));


                // Editing animal ********
            } else if(action == MenuService.EDIT_ANIMAL){
                System.out.println("What is the numeric ID of the animal you want to edit?");
                int x = 0;
                // check if the next input is an int.
                String choice = scanner.nextLine();
                // while loop to check input is valid
                while (MenuService.isInteger(choice) == false || Integer.parseInt(choice) > listOfAnimals.size() ||
                        Integer.parseInt(choice) < 1) {
                    System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                            "Sorry, that is not a valid option. Please try again.\n" + MenuService.ANSI_RESET);
                    choice = scanner.nextLine();
                }
                // return the int the user provided
                x = Integer.parseInt(choice)-1;
                System.out.println("Please answer the following questions. Press enter to keep the current values.");
                // The values
                    // animal name
                System.out.print("Animal name [" +listOfAnimals.get(x).getName() + "]: ");
                String answerName = scanner.nextLine();
                if (answerName.trim().isEmpty()){
                    listOfAnimals.get(x).setName(listOfAnimals.get(x).getName());
                } else {
                    listOfAnimals.get(x).setName(answerName);
                }
                    // animal species
                System.out.print("Animal species [" +listOfAnimals.get(x).getSpecies() + "]: ");
                String answerSpecies = scanner.nextLine();
                if (answerSpecies.trim().isEmpty()){
                    listOfAnimals.get(x).setSpecies(listOfAnimals.get(x).getSpecies());
                } else {
                    listOfAnimals.get(x).setSpecies(answerSpecies);
                }
                    // animal breed
                System.out.print("Animal breed (optional) [" +listOfAnimals.get(x).getBreed() + "]: ");
                String answerBreed = scanner.nextLine();
                if (answerBreed.isEmpty()){
                    listOfAnimals.get(x).setBreed(listOfAnimals.get(x).getBreed());
                } else {
                    listOfAnimals.get(x).setBreed(answerBreed);
                }
                    // animal description
                System.out.print("Animal description [" +listOfAnimals.get(x).getDescription() + "]: ");
                String answerDescription = scanner.nextLine();
                if (answerDescription.trim().isEmpty()){
                    listOfAnimals.get(x).setDescription(listOfAnimals.get(x).getDescription());
                } else {
                    listOfAnimals.get(x).setDescription(answerDescription);
                }
                System.out.println("Animal has been updated!");


                // Delete an animal in the shelter
            } else if (action == MenuService.DELETE_ANIMAL){
                System.out.println("What is the numeric ID of the animal you want to delete?");
                int x = 0;
                // check if the next input is an int.
                String choice = scanner.nextLine();
                // while loop to check input is valid
                while (MenuService.isInteger(choice) == false || Integer.parseInt(choice) > listOfAnimals.size() ||
                        Integer.parseInt(choice) < 1) {
                    System.out.println(MenuService.ANSI_GREEN_BACKGROUND + MenuService.ANSI_RED +
                            "Sorry, that is not a valid option. Please try again.\n" + MenuService.ANSI_RESET);
                    choice = scanner.nextLine();
                }
                // return the int the user provided
                x = Integer.parseInt(choice)-1;
                System.out.println("Are you sure you want to delete " + listOfAnimals.get(x) + "(Y/N)?");
                if (menuService.deleteQuitAnimal() == true){
                    listOfAnimals.remove(x);
                    System.out.println("An animal has been deleted!");
                } else {
                    // nothing happens, continue the while loop;
                    System.out.println("The animal is not extinct YET!");
                }


                // Quit the animal shelter program
            } else if (action == MenuService.QUIT){
                System.out.println("Leaving so soon? All of your data will be lost! (Y/N)");
                if (menuService.deleteQuitAnimal() == true){
                    break;
                } else {
                    // nothing happens, continue the while loop;
                    System.out.println("Welcome back!");
                }
            }
        }
    }
}