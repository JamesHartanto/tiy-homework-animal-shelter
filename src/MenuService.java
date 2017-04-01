import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/28/17.
 */
public class MenuService {
    Scanner scanner;
    public MenuService(Scanner scanner) {
        this.scanner = scanner;
    }

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

    // Listing animals #1
    public void listAnimals(ArrayList<Animals> Animals) {
        if (Animals.isEmpty()){
            System.out.println("All the animals have a home! " +
                    "There is currently no animal living in the shelter!");
        } else {
            System.out.println("--- ANIMALS IN SHELTER ---");
            String name = "";
            String species = "";
            for (int x = 0; x < Animals.size(); x = x + 1){
                name = Animals.get(x).getName();
                species = Animals.get(x).getSpecies();
                System.out.printf("%-5s) %-15s %-15s\n", x + 1, name, species);
            }
        }

    }

    // Creating an animal #2
    public Animals createAnAnimal() {
        System.out.println("---Create an Animal---\nPlease answer the following questions:");
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
        Animals Animal = new Animals(name,species,breed,description);
        return Animal;
    }

    // Viewing a particular animal #3
    public void viewAnimal() {

    }

    // Useful methods
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

}
