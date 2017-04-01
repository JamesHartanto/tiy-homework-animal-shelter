import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by JamesHartanto on 3/28/17.
 */
public class MenuServiceTest {

    ByteArrayOutputStream outputStream;

    @Before
    public void before(){
        this.outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(this.outputStream);
        System.setOut(printStream);
    }

    @Test
    /**
     * Given a menu service
     * When the user enters a number between 1 - 6
     * Then the number is returned
     */
    public void userEnterNumberAndGetsNumberBack(){
        // Arrange
        Scanner scanner = new Scanner("1\n2\n3\n4\n5\n6");
        MenuService menuService = new MenuService(scanner);
        // Act
        int answer1 = menuService.promptForMainMenu();
        int answer2 = menuService.promptForMainMenu();
        int answer3 = menuService.promptForMainMenu();
        int answer4 = menuService.promptForMainMenu();
        int answer5 = menuService.promptForMainMenu();
        int answer6 = menuService.promptForMainMenu();
        // Assert
        assertThat(answer1, equalTo(1));
        assertThat(answer2, equalTo(2));
        assertThat(answer3, equalTo(3));
        assertThat(answer4, equalTo(4));
        assertThat(answer5, equalTo(5));
        assertThat(answer6, equalTo(6));
    }

    @Test
    /**
     * Given a menu service
     * When main menu is prompted
     * Then the main menu is displayed
     */
    public void whenMainMenuPromptedThenMainMenuDisplayed(){
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        // Act
        menuService.promptForMainMenu();
        // Assert
        assertThat(outputStream.toString(),containsString("-- Main Menu --"));
        assertThat(outputStream.toString(),containsString("1) List animals"));
        assertThat(outputStream.toString(),containsString("2) Create an animal"));
        assertThat(outputStream.toString(),containsString("3) View animal details"));
        assertThat(outputStream.toString(),containsString("4) Edit an animal"));
        assertThat(outputStream.toString(),containsString("5) Delete an animal"));
        assertThat(outputStream.toString(),containsString("6) Quit"));
    }

    @Test
    /**
     * Given a menu service
     * When the user inputs a non-integer
     * Then return an error message until an integer is inputted
     */
    public void userInputsNonIntegerThenErrorMessageUntilInteger(){
        // Arrange
        Scanner scanner = new Scanner("haha\nhehe\n5");
        MenuService menuService = new MenuService(scanner);
        // Act
        int answer = menuService.promptForMainMenu();
        // Assert
        assertThat(outputStream.toString(),containsString("Error: Invalid input, haha is not an integer!"));
        assertThat(outputStream.toString(),containsString("Error: Invalid input, hehe is not an integer!"));
        assertThat(answer, equalTo(5));
    }

    @Test
    /**
     * Given a menu service
     * When the user inputs an integer that is not a valid option
     * Then an error message is given until a valid integer is given
     */
    public void userInputsNotValidIntegerReturnErrorUntilValidInteger(){
        // Arrange
        Scanner scanner = new Scanner("8\n-20\n5");
        MenuService menuService = new MenuService(scanner);
        // Act
        int answer = menuService.promptForMainMenu();
        // Assert
        assertThat(outputStream.toString(),containsString("Error: Invalid input, 8 is not a choice in the main menu!"));
        assertThat(outputStream.toString(),containsString("Error: Invalid input, -20 is not a choice in the main menu!"));
        assertThat(answer,equalTo(5));
    }

    @Test
    /**
     * Given the main menu and the user inputs 1 to list animals
     * When there are no animals
     * Then no animals are listed
     */
    public void userInputs1AndNoAnimalsThenNoAnimalsListed(){
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        // Act
        menuService.listAnimals(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),equalTo("All the animals have a home! " +
                "There is currently no animal living in the shelter!\n"));
    }

    @Test
    /**
     * Given a list of 5 animals
     * When the list animal method is called upon
     * Then the animals' name and species are returned
     */
    public void listOf5AnimalsListMethodThenAnimalNameSpeciesReturned(){
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals animal1 = new Animals("Bob","Dinosaur","T-rex","Short Arms");
        Animals animal2 = new Animals("Bill","Monkey","Unknown","Hates Bananas");
        Animals animal3 = new Animals("Nye","Unknown","","Alien?");
        Animals animal4 = new Animals("Science","Human","Nerd","Wears Big Glasses");
        Animals animal5 = new Animals("Guy","Fish","Clownfish","Orange and White");
        AnimalsList.add(animal1);
        AnimalsList.add(animal2);
        AnimalsList.add(animal3);
        AnimalsList.add(animal4);
        AnimalsList.add(animal5);
        // Act
        menuService.listAnimals(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),containsString("--- ANIMALS IN SHELTER ---"));
        assertThat(outputStream.toString(),containsString("Bob"));
        assertThat(outputStream.toString(),containsString("Bill"));
        assertThat(outputStream.toString(),containsString("Nye"));
        assertThat(outputStream.toString(),containsString("Science"));
        assertThat(outputStream.toString(),containsString("Guy"));
        assertThat(outputStream.toString(),containsString("Dinosaur"));
        assertThat(outputStream.toString(),containsString("Monkey"));
        assertThat(outputStream.toString(),containsString("Unknown"));
        assertThat(outputStream.toString(),containsString("Human"));
        assertThat(outputStream.toString(),containsString("Fish"));
    }

    @Test
    /**
     * Given a list of animals
     * When the list method is called
     * Then each animal is indexed
     */
    public void listOfAnimalsListMethodThenAnimalsIndexed() {
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals animal1 = new Animals("Bob", "Dinosaur", "T-rex", "Short Arms");
        Animals animal2 = new Animals("Bill", "Monkey", "Unknown", "Hates Bananas");
        AnimalsList.add(animal1);
        AnimalsList.add(animal2);
        // Act
        menuService.listAnimals(AnimalsList);
        // Assert
        assertThat(outputStream.toString(), containsString("1"));
        assertThat(outputStream.toString(), containsString("2"));
    }

    @Test
    /**
     * Given a main menu
     * When the user inputs 1
     * Then the list animals method is invoked
     */
    public void whenUserInputs1ThenListAnimalsMethod(){
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        // Act
        int listAnimal1 = menuService.promptForMainMenu();
        if (listAnimal1==1){
            menuService.listAnimals(AnimalsList);
        }
        // Assert
        MatcherAssert.assertThat(outputStream.toString(),containsString("All the animals have a home! " +
                "There is currently no animal living in the shelter!\n"));
    }

    @Test
    /**
     * Given the main menu and the user inputs 2 to create animal
     * When an animal is created
     * The list contains the new animal
     */
    public void animalCreatedThenListContainsNewAnimal(){
        // Arrange
        Scanner scanner = new Scanner("Bob\nDinosaur\nLong Neck\nShort legs");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        // Act
        Animals newAnimal = menuService.createAnAnimal();
        AnimalsList.add(newAnimal);
        // Assert
        assertThat(AnimalsList.size(),equalTo(1));
    }

    @Test
    /**
     * Given the main menu and the user inputs 2 to create animal
     * When the user does not put input for not nullable fields (name, species, description)
     * Then an error message is shown and a retry
     */
    public void createAnimalNoInputForNotNullableFieldsErrorMessageShownAndRetry(){
        // Arrange
        Scanner scanner = new Scanner("\n" +
                "Bob\n" +
                "\n" +
                "Dinosaur\n" +
                "\n" +
                "\n" +
                "Short Legs\n");
        MenuService menuService = new MenuService(scanner);
        // Act
        Animals Animal = menuService.createAnAnimal();
        // Assert
        assertThat(outputStream.toString(),containsString("Error: The name for the animal is required! Please try again!\n"));
        assertThat(outputStream.toString(),containsString("Error: The species for the animal is required! Please try again!\n"));
        assertThat(outputStream.toString(),containsString("Error: The description for the animal is required! Please try again!\n"));
        assertThat(Animal.getName(),equalTo("Bob"));
        assertThat(Animal.getSpecies(),equalTo("Dinosaur"));
        assertThat(Animal.getBreed(),equalTo(""));
        assertThat(Animal.getDescription(),equalTo("Short Legs"));
    }

    // @Test
    /**
     * Given a main menu and a list with 2 animals
     * When view animal is prompted
     * Then the animal details at the particular index is shown
     */

    }

}