import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/28/17.
 */
public class MenuService {
    Scanner scanner;
    public MenuService(Scanner scanner) throws SQLException {
        this.scanner = scanner;
    }

    String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";
    AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);

    // Main menu
    public int promptForMainMenu() {
        System.out.println("-- Main Menu --\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit");
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
            String badInput = scanner.nextLine();
            System.out.println("Error: Invalid input, " + badInput + " is not an integer!");
            return promptForMainMenu();
        }
    }

    // Listing animals #1 NOT NECESSARY SINCE USING REPOSITORY METHOD
//    public void listAnimals(ArrayList<Animals> AnimalsList) throws SQLException {
//        System.out.println("--List Animals--");
//        if (AnimalsList.isEmpty()){
//            System.out.println("All the animals have a home! " +
//                    "There is currently no animal living in the shelter!");
//        } else {
//            System.out.println("--- ANIMALS IN SHELTER ---");
//            String name = "";
//            String species = "";
//            for (int x = 0; x < AnimalsList.size(); x = x + 1){
//                name = AnimalsList.get(x).getName();
//                species = AnimalsList.get(x).getSpecies();
//                System.out.printf("%-5s) %-15s %-15s\n", x + 1, name, species);
//            }
//        }
//    }

    // Creating an animal #2
    public void createAnAnimal() throws SQLException {
        System.out.println("--Create an Animal--\nPlease answer the following questions:");

        // Animal name
        String name = promptForString("What is the animal's name?");
        System.out.println("The name of the animal is: " + name);

        // Species
        String species = promptForString("What is the species of the animal?");
        System.out.println("The species of the animal is: " + species);

        // Breed
        System.out.println("What is the breed of the animal? (optional)");
        String breed = scanner.nextLine();

        // Description
        String description = promptForString("Please describe the animal!");
        System.out.println("Description: " + description);

        // Creating the animal
//        return new Animals(name,species,breed,description);
        Animals animalToSave = new Animals(name,species,breed,description);
        animalRepository.saveAnimal(animalToSave);
        System.out.println("Animal saved to database!");
    }

    // Viewing a particular animal #3
    public void viewAnimal(ArrayList<Animals> AnimalsList) {
        System.out.println("--View an Animal--");
        if (AnimalsList.size() == 0) {
            System.out.println("There are no animals to view!");
        } else {
            System.out.println("What is the numeric ID of the animal you want to view?");

            // Creating variable to store user input
            int input = 0;

            // Checks if user input is an integer
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();

                // Checks to see if integer is in the list
                if (input > AnimalsList.size() || input < 1) {
                    System.out.println(input + " is not part of the list! Please try again!");
                    viewAnimal(AnimalsList);

                } else {
                    System.out.println(AnimalsList.get(input - 1));
                    // animal repository listing animal ??
                }

            } else {
                String badInput = scanner.nextLine();
                System.out.println("Error: Invalid input! " + badInput + " is not an integer!");
                viewAnimal(AnimalsList);
            }
        }
    }

    // Editing an animal #4
    public void editAnimal(ArrayList<Animals> AnimalsList) {
        System.out.println("--Edit Animal--");
        // making sure there are animals in the list
        if (AnimalsList.size() == 0) {
            System.out.println("There are no animals! Create an animal first!");
            // edit animal if there are animals in the list
        } else {
            System.out.println("What is the numeric ID of the animal you want to edit?");

            // Checks if user input is an integer - needed to use Integer.parseInt so test can read next line properly
            String input = scanner.nextLine();
            while (!isInteger(input) || Integer.parseInt(input) > AnimalsList.size() ||
                    Integer.parseInt(input) < 1) {
                System.out.println("Sorry, that is not a valid input. Please try again.\n");
                input = scanner.nextLine();
            }
            int userInt = Integer.parseInt(input);

            // EDITING THE ANIMAL
            System.out.println("Please answer the following questions. Press enter to keep the current values.");

            // name
            System.out.print("Animal name [" + AnimalsList.get(userInt - 1).getName() + "]: ");
            String name = scanner.nextLine().trim();
            // only changes value if there is a non-empty entry
            if (!name.isEmpty()) {
                AnimalsList.get(userInt - 1).setName(name);
            }
            System.out.println("Animal name is now set to: " + AnimalsList.get(userInt - 1).getName());

            // species
            System.out.print("Animal species [" + AnimalsList.get(userInt - 1).getSpecies() + "]: ");
            String species = scanner.nextLine().trim();
            if (!species.isEmpty()) {
                AnimalsList.get(userInt - 1).setSpecies(species);
            }
            System.out.println("Animal species is now set to: " + AnimalsList.get(userInt - 1).getSpecies());

            // breed - no trim because it can be edited to nothing
            System.out.print("Animal breed (optional) [" + AnimalsList.get(userInt - 1).getBreed() + "]: ");
            String breed = scanner.nextLine().trim();
            if (!breed.isEmpty()) {
                AnimalsList.get(userInt - 1).setBreed(breed);
            }
            System.out.println("Animal breed is now set to: " + AnimalsList.get(userInt - 1).getBreed());

            // description
            System.out.print("Animal description [" + AnimalsList.get(userInt - 1).getDescription() + "]: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                AnimalsList.get(userInt - 1).setDescription(description);
            }
            System.out.println("Animal description is: " + AnimalsList.get(userInt - 1).getDescription());

            // SUCCESS !!
            System.out.println("Animal has been successfully updated!");
        }
    }


    // Deleting an animal
    public void deleteAnimal(ArrayList<Animals> AnimalsList) {
        System.out.println("--Delete an Animal--");
        if (AnimalsList.size() == 0){
            System.out.println("There are no animals in the shelter to delete!");
        } else {
            System.out.println("What is the numeric ID of the animal you want to delete?");
            // Creating variable to store user input
            int input = 0;

            // Checks if user input is an integer
            if (scanner.hasNextInt()){
                input = scanner.nextInt();

                // Checks to see if integer is in the list
                if (input > AnimalsList.size() || input < 1){
                    System.out.println(input + " is not part of the list! Please try again!");
                    deleteAnimal(AnimalsList);

                } else {
                    // Confirmation of deletion
                    System.out.println("Are you sure you want to delete " + AnimalsList.get(input-1) + "? (Y/N)");
                    if (deleteQuitConfirmation()){
                        AnimalsList.remove(input-1);
                        System.out.println("An animal has been deleted!");
                    } else {
                        System.out.println("The animal is safe!");
                    }
                }
            // if user did not input integer, prompt again
            } else {
                String badInput = scanner.nextLine();
                System.out.println("Error: Invalid input! " + badInput + " is not an integer!");
                deleteAnimal(AnimalsList);
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
        String input = scanner.nextLine().trim();
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
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y") ||
                choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
            return choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y");
        } else{
            System.out.println("Sorry that is an invalid input! Please try again.");
            return deleteQuitConfirmation();
        }
    }
}
