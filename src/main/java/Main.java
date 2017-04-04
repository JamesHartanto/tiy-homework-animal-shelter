import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 4/2/17.
 */
public class Main {
    public static Scanner scanner;

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";
        AnimalRepository repository = new AnimalRepository(jdbcUrl);

//        MenuService menuService = new MenuService(scanner);
//        while(true){
//            int selection = menuService.promptForMainMenu();
//
//            if (selection==1){
//                menuService.listAnimals(AnimalList);
//            } else if (selection==2){
//                AnimalList.add(menuService.createAnAnimal());
//            } else if (selection==3){
//                menuService.viewAnimal(AnimalList);
//            } else if (selection==4){
//                menuService.editAnimal(AnimalList);
//            } else if (selection==5){
//                menuService.deleteAnimal(AnimalList);
//            } else if (selection==6){
//                if (menuService.exitAnimal()){
//                    break;
//                };
//            }
//        }
    }
}
