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
                    // Just creating a new line
                    System.out.println();
                } else {
                    System.out.println("All the animals have a home! There is currently no animal living in the shelter!\n");
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
                    System.out.println("An animal has successfully been added to the database!\n");
                } else {
                    System.out.println("Nothing happened\n");
                }


            // VIEW A PARTICULAR ANIMAL
            } else if (selection==3){
                if (animalRepository.countAnimals() == 0){
                    System.out.println("There are no animals to view!");
                } else {
                    // Using menuService to see what animal to see
                    // Getting animal data from database and printing it out
                    System.out.println(animalRepository.readAnimalID(menuService.viewAnimal(animalRepository.listAnimals()))+"\n");
                }

            // EDITING AN ANIMAL
            } else if (selection==4){
                if (animalRepository.countAnimals() == 0){
                    System.out.println("There are no animals! Create an animal first!\n");
                }else{
                    // Using menuService to see what animal to edit, getting the animal from repository
                    Animal animalToEdit = animalRepository.readAnimalID(menuService.editAnimalNumber(animalRepository.listAnimals()));
                    // Editing the values of the animal before sending data to database
                    animalRepository.editAnimalID(menuService.editAnimalValues(animalToEdit));
                    // just creating a new line
                    System.out.println();
                }


            // DELETING AN ANIMAL
            } else if (selection==5){
                // Checking if there are any animals
                if (animalRepository.countAnimals() == 0) {
                    System.out.println("There are no animals in the shelter to delete!\n");
                } else{
                    // Using menuService to see what animal to delete, if returns 0 then nothing happens
                    int animalToDelete = menuService.deleteAnimal(animalRepository.listAnimals());
                    if (animalToDelete != 0) {
                        animalRepository.deleteAnimal(animalToDelete);
                        System.out.println("An animal has been deleted!");
                    }
                }


            // QUIT/LEAVE THE SHELTER
            } else if (selection==6){
                if (menuService.exitAnimal()){
                    break;
                };
            }
        }
    }
}
