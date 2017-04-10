import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/28/17.
 */
public class MenuService {

    Scanner scanner;
    AnimalRepository animalRepository;

    public MenuService(Scanner scanner, AnimalRepository animalRepository) throws SQLException {
        this.scanner = scanner;
        this.animalRepository = animalRepository;
    }

    // Main menu
    public int promptForMainMenu() {
        System.out.println("-- Main Menu --\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n" +
                "Please choose an option:");
        int input = 0;
        if (scanner.hasNextInt()){
            input = scanner.nextInt();
            if (input > 6 || input < 1){
                System.out.println("Error: Invalid input, " + input + " is not a choice in the main menu!");
                return promptForMainMenu();
            } else {
                return input;
            }
        } else {
            String badInput = scanner.next();
            System.out.println("Error: Invalid input, " + badInput + " is not an integer!");
            return promptForMainMenu();
        }
    }

    // Listing animals #1
    public void listAnimal(ArrayList<Animal> animalList){
        System.out.println("--List of Animals--");
        if (animalList.size() == 0){
            System.out.println("All the animals have a home! " +
                    "There is currently no animal living in the shelter!");
        } else {
            for (int x = 0; x < animalList.size(); x = x + 1){
                System.out.print(x + 1 + ")");
                System.out.printf("NAME: %s   SPECIES: %s   BREED: %s   DESCRIPTION: %s\n",
                        animalList.get(x).getName(),
                        animalList.get(x).getSpecies(),
                        animalList.get(x).getBreed(),
                        animalList.get(x).getDescription());
            }
        }
    }


    // Creating an animal #2
    public Animal createAnAnimal() throws SQLException {
        System.out.println("--Create an Animal--\nPlease answer the following questions:");

        // Animal name
        String name = promptForString("What is the animal's name?");
        System.out.println("The name of the animal is: " + name);

        // Species
        String species = promptForString("What is the species of the animal?");
        System.out.println("The species of the animal is: " + species);

        // Breed
        System.out.println("What is the breed of the animal? (optional)");
        String breed = scanner.next();
        if (!breed.isEmpty()){
            System.out.println("The breed of the animal is: " + breed);
        }

        // Description
        String description = promptForString("Please describe the animal:");
        System.out.println("Description: " + description);

        // Creating the animal
        return new Animal(name,species,breed,description);
    }

    // Viewing a particular animal #3
    public int viewAnimal() throws SQLException {
        System.out.println("--View an Animal--");
        // Creating variable to store user input
        int input = 0;
        if (animalRepository.countAnimals() == 0) {
            System.out.println("There are no animals to view!");
        } else {
            System.out.println("What is the numeric ID of the animal you want to view?");

            // Checks if user input is an integer
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();

                // Checks to see if integer is in the list
                if (input > animalRepository.countAnimals() || input < 1) {
                    System.out.println(input + " is not part of the list! Please try again!");
                    viewAnimal();

                } else {
                    return input;
                }

            } else {
                String badInput = scanner.next();
                System.out.println("Error: Invalid input! " + badInput + " is not an integer!");
                viewAnimal();
            }
        }
        return input;
    }

    // Editing an animal #4
    public void editAnimal() throws SQLException {
//    public void editAnimal(ArrayList<Animal> AnimalsList) throws SQLException {
        System.out.println("--Edit Animal--");
        // making sure there are animals in the list
        if (animalRepository.countAnimals() == 0) {
            System.out.println("There are no animals! Create an animal first!");
            // edit animal if there are animals in the list
        } else {
            System.out.println("What is the numeric ID of the animal you want to edit?");

            // Checks if user input is an integer - needed to use Integer.parseInt so test can read next line properly
            String input = scanner.next();
            while (!isInteger(input) || Integer.parseInt(input) > animalRepository.countAnimals() ||
                    Integer.parseInt(input) < 1) {
                System.out.println("Sorry, that is not a valid input. Please try again.\n");
                input = scanner.next();
            }
            int userInt = Integer.parseInt(input);

            // EDITING THE ANIMAL
            Animal animalToEdit = animalRepository.readAnimalID(userInt);
            System.out.println("Please answer the following questions. Press enter to keep the current values.");

            // name
            System.out.print("Animal name [" + animalToEdit.getName() + "]: ");
            String name = scanner.next().trim();
            // only changes value if there is a non-empty entry
            if (!name.isEmpty()) {
                animalToEdit.setName(name);
            }
            System.out.println("Animal name is now set to: " + animalToEdit.getName());

            // species
            System.out.print("Animal species [" + animalToEdit.getSpecies() + "]: ");
            String species = scanner.next().trim();
            if (!species.isEmpty()) {
                animalToEdit.setSpecies(species);
            }
            System.out.println("Animal species is now set to: " + animalToEdit.getSpecies());

            // breed - no trim because it can be edited to nothing
            System.out.print("Animal breed (optional) [" + animalToEdit.getBreed() + "]: ");
            String breed = scanner.next().trim();
            if (!breed.isEmpty()) {
                animalToEdit.setBreed(breed);
            }
            System.out.println("Animal breed is now set to: " + animalToEdit.getBreed());

            // description
            System.out.print("Animal description [" + animalToEdit.getDescription() + "]: ");
            String description = scanner.next().trim();
            if (!description.isEmpty()) {
                animalToEdit.setDescription(description);
            }
            System.out.println("Animal description is: " + animalToEdit.getDescription());

            // SUCCESS !!
            animalRepository.editAnimalID(userInt,animalToEdit);
            System.out.println("Animal has been successfully updated!");
        }
    }


    // Deleting an animal
    public void deleteAnimal() throws SQLException {
//    public void deleteAnimal(ArrayList<Animal> AnimalsList) throws SQLException {
        System.out.println("--Delete an Animal--");
        if (animalRepository.countAnimals() == 0){
            System.out.println("There are no animals in the shelter to delete!");
        } else {
            System.out.println("What is the numeric ID of the animal you want to delete?");
            // Creating variable to store user input
            int input = 0;

            // Checks if user input is an integer
            if (scanner.hasNextInt()){
                input = scanner.nextInt();

                // Checks to see if integer is in the list
                if (input > animalRepository.countAnimals() || input < 1){
                    System.out.println(input + " is not part of the list! Please try again!");
                    deleteAnimal();

                } else {
                    // Confirmation of deletion
                    System.out.println("Are you sure you want to delete " + animalRepository.readAnimalID(input) + "? (Y/N)");
                    if (deleteQuitConfirmation()){
                        animalRepository.deleteAnimal(input);
                        System.out.println("An animal has been deleted!");
                    } else {
                        System.out.println("The animal is safe!");
                    }
                }
                // if user did not input integer, prompt again
            } else {
                String badInput = scanner.next();
                System.out.println("Error: Invalid input! " + badInput + " is not an integer!");
                deleteAnimal();
            }
        }
    }


    // Exit program
    public boolean exitAnimal() {
        System.out.println("--Exit Animal Shelter--");
        System.out.println("Are you sure you want to leave so soon? (Y/N)");
        if (deleteQuitConfirmation()){
            System.out.println("Good bye! Hope to see you again soon!");
            return true;
        } else{
            System.out.println("Welcome back!");
            return false;
        }
    }


    // USEFUL METHODS
    // Checking if input is not empty
    private String promptForString(String message) {
        System.out.println(message);
        String input = scanner.next().trim();
        if (input.isEmpty()){
            System.out.println("Error: You did not input anything!");
            return promptForString(message);
        } else {
            return input;
        }
    }

    // Checking if input is integer, returns true or false
    private static boolean isInteger(String str) {
        try {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Confirmation for delete/quit
    private boolean deleteQuitConfirmation() {
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y") ||
                choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
            return choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y");
        } else{
            System.out.println("Sorry that is an invalid input! Please try again.");
            return deleteQuitConfirmation();
        }
    }
}
