package com.theironyard;

import java.util.Scanner;

/**
 * Created by JamesHartanto on 3/20/17.
 */
public class Main {
    public static void main(String[] args) {
        // Creating instances to use classes
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService(scanner);
        Animal animal = new Animal();

        while(true) {
            int action = menuService.promptForMainMenuSelection();

            if(action == MenuService.CREATE_ANIMAL){
                // do something
            } else if(action == MenuService.LIST_ANIMALS){
                // do something
            }
        }

    }
}
