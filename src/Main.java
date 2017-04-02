import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JamesHartanto on 4/2/17.
 */
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Animals> AnimalList = new ArrayList<>();

    public static void main(String[] args) {
        MenuService menuService = new MenuService(scanner);
        while(true){
            int selection = menuService.promptForMainMenu();

            if (selection==1){
                menuService.listAnimals(AnimalList);
            } else if (selection==2){
                menuService.createAnAnimal();
            } else if (selection==3){
                menuService.viewAnimal(AnimalList);
            } else if (selection==4){
                menuService.editAnimal(AnimalList);
            } else if (selection==5){
                menuService.deleteAnimal(AnimalList);
            } else if (selection==6){
                if (menuService.exitAnimal()){
                    break;
                };
            }
        }
    }
}
