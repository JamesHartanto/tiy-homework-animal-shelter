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
        for (int x = 0; x < animalList.size(); x = x + 1){
            System.out.printf("ID: %-5s Name: %-20s Species: %-20s Breed: %-20s Description: %-50s\n",
                    animalList.get(x).getId(), animalList.get(x).getName(),animalList.get(x).getSpecies(),
                    animalList.get(x).getBreed(), animalList.get(x).getDescription());
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
    public int viewAnimal(ArrayList<Animal> animalList) throws SQLException {
        System.out.println("--View an Animal--");
        // Creating variable to store user input
        int input = waitForInt("What is the numeric ID of the animal you want to view?");

        // Creates a list of integers that contains the id
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int x = 0; x < animalList.size(); x = x + 1){
            numbers.add(animalList.get(x).getId());
        }
        // Checks to see if integer is in the list
        if (!numbers.contains(input)){
            System.out.println(input + " is not part of the list! Please try again!");
            return viewAnimal(animalList);
        }
        return input;
    }

    // Editing an animal #4
    public int editAnimalNumber(ArrayList<Animal> animalList) throws SQLException {
        System.out.println("--Edit Animal--");

        // Checks if user input is an integer
        int input = waitForInt("What is the numeric ID of the animal you want to edit?");

        // Creates a list of integers that contains the id
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int x = 0; x < animalList.size(); x = x + 1) {
            numbers.add(animalList.get(x).getId());
        }
        // Checks to see if integer is in the list
        if (!numbers.contains(input)) {
            System.out.println(input + " is not part of the list! Please try again!");
            return editAnimalNumber(animalList);
        }
        return input;
    }

        public Animal editAnimalValues(Animal animalToEdit) {
            // EDITING THE ANIMAL
            System.out.println("Please answer the following questions. Press enter to keep the current values.");

            // name
            System.out.print("Animal name [" + animalToEdit.getName() + "]: ");
            String name = scanner.next();
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

            return animalToEdit;
        }


    // Deleting an animal
    public int deleteAnimal(ArrayList<Animal> animalList) throws SQLException {
//    public void deleteAnimal(ArrayList<Animal> AnimalsList) throws SQLException {
        System.out.println("--Delete an Animal--");
        // Creating variable to store user input
        int input = waitForInt("What is the numeric ID of the animal you want to delete?");

        // Creates a list of integers that contains the id
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int x = 0; x < animalList.size(); x = x + 1){
            numbers.add(animalList.get(x).getId());
        }
        // Checks to see if integer is in the list
        if (!numbers.contains(input)){
            System.out.println(input + " is not part of the list! Please try again!");
            return deleteAnimal(animalList);
        }

        // Confirmation of deletion
        System.out.println("Are you sure you want to delete animal id: " + input + "? (Y/N)");
        if (deleteQuitConfirmation()){
            return input;
        } else {
            System.out.println("The animal is safe!");
            return 0;
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

    private int waitForInt(String prompt) {
        // display the prompt to the user
        System.out.print(prompt.trim() + " ");
        // check if the next input is an int.
        if (!scanner.hasNextInt()) {
            // if the next input is not an int, read it as a string to show in an error message
            String badInput = scanner.next();
            // show an error message
            System.out.printf("\nError: '%s' is not a valid number. Please try again.\n", badInput);
            // recursively prompt the user again
            return waitForInt(prompt);
        } else {
            // return the int the user provided
            return scanner.nextInt();
        }
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
