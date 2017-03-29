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
                System.out.printf("%-5s) %-15s %-15s", x + 1, name, species);
            }
        }

    }
}
