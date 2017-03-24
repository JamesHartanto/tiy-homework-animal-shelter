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
                // shows the list of animals
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
                System.out.println("What is the breed?");
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

            } else if(action == MenuService.VIEW_ANIMAL_DETAILS){
                // Asks user for which animal to see
                System.out.println("Which animal would you like to know more about?");
                String animalToKnowAbout = scanner.nextLine();
                int indexOfAnimalToKnowAbout = animal.listOfAnimals.indexOf(animalToKnowAbout);
                animal.listOfAnimals.get(indexOfAnimalToKnowAbout);

            } else if(action == MenuService.EDIT_ANIMAL){
                //When editing an animal, don't make users retype everything.
                // See the suggestions below regarding editing an animal.
                // Hint: You could create a method on MenuService that accepts an Animal as an argument and,
                // for each field, if the user doesn't provide a new value, it reuses the current value.

            } else if (action == MenuService.DELETE_ANIMAL){

            } else if (action == MenuService.QUIT){

            };
        }
    }
}
