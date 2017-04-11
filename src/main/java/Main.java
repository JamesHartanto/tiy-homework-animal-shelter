import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 4/2/17.
 */
public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        scanner.useDelimiter("\n");
        String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);

        while(true){
            int selection = menuService.promptForMainMenu();

            // LIST ANIMALS
            if (selection==1){
                // Getting the arraylist from repository
                // Printing out all the animals
                menuService.listAnimal(animalRepository.listAnimals());

            // CREATE ANIMAL
            } else if (selection==2){
                // Using menuService to get details of the new animal
                // Adding the animal to the database
                animalRepository.saveAnimal(menuService.createAnAnimal());

            // VIEW A PARTICULAR ANIMAL
            } else if (selection==3){
                // Using menuService to see what animal to see
                // Getting animal data from database and printing it out
                animalRepository.readAnimalID(menuService.viewAnimal());

            // EDITING AN ANIMAL
            } else if (selection==4){
                // Using menuService to see what animal to edit and storing as variables
                int animalNumberToEdit = menuService.editAnimalNumber();
                Animal animalToEdit = menuService.editAnimalInputs(animalNumberToEdit);
                // Sending data to database
                animalRepository.editAnimalID(animalNumberToEdit,animalToEdit);
            } else if (selection==5){
                menuService.deleteAnimal();
            } else if (selection==6){
                if (menuService.exitAnimal()){
                    break;
                };
            }
        }
    }
}
