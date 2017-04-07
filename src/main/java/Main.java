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
        AnimalRepository animalrepository = new AnimalRepository(jdbcUrl);

        MenuService menuService = new MenuService(scanner, animalrepository);
        while(true){
            int selection = menuService.promptForMainMenu();

            if (selection==1){
                animalrepository.listAnimals();
            } else if (selection==2){
                menuService.createAnAnimal();
            } else if (selection==3){
                menuService.viewAnimal();
            } else if (selection==4){
                menuService.editAnimal();
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
