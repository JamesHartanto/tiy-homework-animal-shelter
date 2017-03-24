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
        // Creating instances to use classes
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService(scanner);
        Animal animal = new Animal();

        while(true) {
            int action = menuService.promptForMainMenuSelection();

            if(action == MenuService.LIST_ANIMALS){
                for (int x = 0; x < animal.listOfAnimals.size(); x = x + 1) {
                    String format1 = "%-5s%s%n";
                    //String format1 = "%2d. %-20s $%.2f%n";
                    System.out.printf("%s)   %s   %s\n",
                            x+1,
                            animal.listOfAnimals.get(x).getName(),
                            animal.listOfAnimals.get(x).getSpecies());
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
                animal.listOfAnimals.add(0,newAnimal);
                System.out.println("Animal has been created!");


                // Viewing animal details
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS){
                // Asks user for which animal to see
                System.out.println("What is the numeric ID of the animal you want to view?");
                int animalToKnowAbout = menuService.viewAnimalInt();
                animal.listOfAnimals.get(animalToKnowAbout+1);


                // Editing animal
            } else if(action == MenuService.EDIT_ANIMAL){
                //When editing an animal, don't make users retype everything.
                // See the suggestions below regarding editing an animal.
                // Hint: You could create a method on MenuService that accepts an Animal as an argument and,
                // for each field, if the user doesn't provide a new value, it reuses the current value.
                System.out.println("What is the numeric ID of the animal you want to edit?");
                int animalToKnowAbout = menuService.viewAnimalInt();

                animal.listOfAnimals.get(animalToKnowAbout);




                // Delete an animal in the shelter
            } else if (action == MenuService.DELETE_ANIMAL){
                System.out.println("What is the numeric ID of the animal you want to delete?");
                int animalToKnowAbout = menuService.viewAnimalInt();
                System.out.println("Are you sure you want to delete:\n");
                animal.listOfAnimals.get(animalToKnowAbout+1);
                if (menuService.deleteQuitAnimal() == true){
                    animal.listOfAnimals.remove(animalToKnowAbout+1);
                } else
                    {
                    // nothing happens, continue the while loop
                }


                // Quit the animal shelter program
            } else if (action == MenuService.QUIT){
                System.out.println("Are you sure you want to quit? All of your data will be lost!");
                if (menuService.deleteQuitAnimal() == true){
                    break;
                } else {
                    // nothing happens, continue the while loop;
                }
            }
        }
    }
}
