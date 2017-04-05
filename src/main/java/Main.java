import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 4/2/17.
 */
public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";
        AnimalRepository animalrepository = new AnimalRepository(jdbcUrl);

        AnimalService animalService = new AnimalService(scanner);
        while(true){
            int selection = animalService.promptForMainMenu();

            if (selection==1){
//                menuService.listAnimals(AnimalList);
                animalrepository.listAnimals();
            } else if (selection==2){
                animalService.createAnAnimal();
            } else if (selection==3){
                animalService.viewAnimal();
            } else if (selection==4){
                animalService.editAnimal();
            } else if (selection==5){
                animalService.deleteAnimal();
            } else if (selection==6){
                if (animalService.exitAnimal()){
                    break;
                };
            }
        }
    }
}
