import java.sql.SQLException;
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
                // Checking database if there are any animals
                if (animalRepository.countAnimals() > 0){
                    // Getting the arraylist from repository and printing it out
                    menuService.listAnimal(animalRepository.listAnimals());
                } else {
                    System.out.println("All the animals have a home! There is currently no animal living in the shelter!");
                }


            // CREATE ANIMAL
            } else if (selection==2){
                // Counting animal before creation
                int before = animalRepository.countAnimals();
                // Using menuService to get details of the new animal
                // Adding the animal to the database
                animalRepository.saveAnimal(menuService.createAnAnimal());
                // Was the creation successful?
                int after = animalRepository.countAnimals();
                if (after > before){
                    System.out.println("An animal has successfully been added to the database!");
                } else {
                    System.out.println("Nothing happened");
                }


            // VIEW A PARTICULAR ANIMAL
            } else if (selection==3){
                if (animalRepository.countAnimals() == 0){
                    System.out.println("There are no animals to view!");
                } else {
                    // Using menuService to see what animal to see
                    // Getting animal data from database and printing it out
                    animalRepository.readAnimalID(menuService.viewAnimal(animalRepository.listAnimals()));
                }

            // EDITING AN ANIMAL
            } else if (selection==4){
                if (animalRepository.countAnimals() == 0){
                    System.out.println("There are no animals! Create an animal first!");
                }else{
                // Using menuService to see what animal to edit and storing as variables
                // Sending data to database
                animalRepository.editAnimalID(menuService.editAnimal(animalRepository.listAnimals()));
                }


            // DELETING AN ANIMAL
            } else if (selection==5){
                // Using menuService to see what animal to delete and trying it in the database
                animalRepository.deleteAnimal(menuService.deleteAnimal());


            // QUIT/LEAVE THE SHELTER
            } else if (selection==6){
                if (menuService.exitAnimal()){
                    break;
                };
            }
        }
    }
}
