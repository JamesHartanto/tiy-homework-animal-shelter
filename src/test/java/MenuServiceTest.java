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
        assertThat(outputStream.toString(),equalTo("--List Animals--\n" +
                "All the animals have a home! There is currently no animal living in the shelter!\n"));
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
        assertThat(outputStream.toString(),containsString("Error: You did not input anything!\n"));
        assertThat(Animal.getName(),equalTo("Bob"));
        assertThat(Animal.getSpecies(),equalTo("Dinosaur"));
        assertThat(Animal.getBreed(),equalTo(""));
        assertThat(Animal.getDescription(),equalTo("Short Legs"));
    }

    @Test
    /**
     * Given a main menu and a list with 2 animals
     * When view animal 2 is prompted
     * Then the animal details at the particular index is shown
     */
    public void viewAnimalPromptedThenAnimalDetailsAtParticularIndexShown(){
        // Arrange
        Scanner scanner = new Scanner("2");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals Animal1 = new Animals("Zack","Tower","Bravo","Tango");
        Animals Animal2 = new Animals("Bobby","Billy","Zappy","Stringy");
        AnimalsList.add(Animal1);
        AnimalsList.add(Animal2);
        // Act
        menuService.viewAnimal(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),containsString("Bobby"));
        assertThat(outputStream.toString(),containsString("Billy"));
        assertThat(outputStream.toString(),containsString("Zappy"));
        assertThat(outputStream.toString(),containsString("Stringy"));
    }

    @Test
    /**
     * Given a main menu with 3 animals
     * When bad inputs are given for view animal
     * Then error messages are prompted until valid inputs are inserted
     */
    public void badInputsGivenForViewAnimalThenErrorMessageUntilValidInput() {
        // Arrange
        Scanner scanner = new Scanner("5\nDinosaur\n0\n3");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals Animal1 = new Animals("Zack", "Tower", "Bravo", "Tango");
        Animals Animal2 = new Animals("Bobby", "Billy", "Zappy", "Stringy");
        Animals Animal3 = new Animals("Father", "Mother", "Brother", "Sister");
        AnimalsList.add(Animal1);
        AnimalsList.add(Animal2);
        AnimalsList.add(Animal3);
        // Act
        menuService.viewAnimal(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),containsString("5 is not part of the list! Please try again!"));
        assertThat(outputStream.toString(),containsString("Error: Invalid input! Dinosaur is not an integer!"));
        assertThat(outputStream.toString(),containsString("0 is not part of the list! Please try again!"));
        assertThat(outputStream.toString(),containsString("Father"));
        assertThat(outputStream.toString(),containsString("Mother"));
        assertThat(outputStream.toString(),containsString("Brother"));
        assertThat(outputStream.toString(),containsString("Sister"));
    }


    @Test
    /**
     * Given a main menu
     * When edit animal is prompted
     * Then edit animal messages come out
     */
    public void EditAnimalIsPromptedThenEditMessages(){
        // Arrange
        Scanner scanner = new Scanner("1\nnope\nyup\nhappy\nsad");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals Animal1 = new Animals("Luffy", "Pirate", "Rubber", "Captain");
        AnimalsList.add(Animal1);
        // Act
        menuService.editAnimal(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),containsString("What is the numeric ID of the animal you want to edit?"));
        assertThat(outputStream.toString(),containsString("Please answer the following questions. Press enter to keep the current values."));
        assertThat(outputStream.toString(),containsString("Animal name"));
        assertThat(outputStream.toString(),containsString("Animal species"));
        assertThat(outputStream.toString(),containsString("Animal breed (optional)"));
        assertThat(outputStream.toString(),containsString("Animal description"));
    }

    @Test
    /**
     * Given a main menu with an animal
     * When bad inputs are given
     * Then error messages are shown
     */
    public void BadInputsGivenThenErrorMessagesShown(){
        Scanner scanner = new Scanner("5\n0\n1\nZoro the swordsman\n123\n \nCaptain");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalsList = new ArrayList<>();
        Animals Animal1 = new Animals("Luffy", "Pirate", "Rubber", "Orphan");
        AnimalsList.add(Animal1);
        // Act
        menuService.editAnimal(AnimalsList);
        // Assert
        assertThat(outputStream.toString(),containsString("Sorry, that is not a valid input. Please try again."));
        assertThat(outputStream.toString(),containsString("Sorry, that is not a valid input. Please try again."));
        assertThat(AnimalsList.get(0).getName(),equalTo("Zoro the swordsman"));
        assertThat(AnimalsList.get(0).getSpecies(),equalTo("123"));
        assertThat(AnimalsList.get(0).getBreed(),equalTo("Rubber"));
        assertThat(AnimalsList.get(0).getDescription(),equalTo("Captain"));
    }

    @Test
    /**
     * Given a main menu
     * When there is no animal in the list for editing
     * Then it displayed error message and exits
     */
    public void noAnimalInListForEditingThenErrorMessageAndExit(){
        // Arrange
        Scanner scanner = new Scanner("1\n");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalList = new ArrayList<Animals>();
        // Act
        menuService.editAnimal(AnimalList);
        // Assert
        assertThat(outputStream.toString(),containsString("There are no animals! Create an animal first!"));
    }


    @Test
    /**
     * Given a main menu with no animals
     * When the delete method is prompted
     * Then a delete message is shown
     */
    public void deleteMethodPromptedThenDeleteMessageShown(){
        // Arrange
        Scanner scanner = new Scanner("1");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalList = new ArrayList<>();
        // Act
        menuService.deleteAnimal(AnimalList);
        // Assert
        assertThat(outputStream.toString(),containsString("--Delete an Animal--"));
        assertThat(outputStream.toString(),containsString("There are no animals in the shelter to delete!"));
    }

    @Test
    /**
     * Given a main menu with three animals
     * When the delete method is prompted to delete animal 2 and confirmed yes
     * Then the list has two animals remaining
     */
    public void threeAnimalsDeleteToTwoRemaining(){
        // Arrange
        Scanner scanner = new Scanner("2\nYes");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalList = new ArrayList<>();
        Animals Animal1 = new Animals("Name1", "Pirate", "Good guy", "Has boat");
        Animals Animal2 = new Animals("Name2", "Zombie", "", "Can't swim");
        Animals Animal3 = new Animals("Name3", "Navy", "Fireman", "Battleship");
        AnimalList.add(Animal1);
        AnimalList.add(Animal2);
        AnimalList.add(Animal3);
        // Act
        menuService.deleteAnimal(AnimalList);
        // Assert
        assertThat(outputStream.toString(),containsString("Are you sure you want to delete"));
        assertThat(outputStream.toString(),containsString("An animal has been deleted"));
        assertThat(2,equalTo(AnimalList.size()));
    }

    @Test
    /**
     * Given a main menu with three animals
     * When the delete method is prompted to delete animal 2 and confirmed no
     * Then the list still has three animals remaining
     */
    public void threeAnimalsDeleteStillThreeRemaining(){
        // Arrange
        Scanner scanner = new Scanner("2\nNo");
        MenuService menuService = new MenuService(scanner);
        ArrayList<Animals> AnimalList = new ArrayList<>();
        Animals Animal1 = new Animals("Name1", "Pirate", "Good guy", "Has boat");
        Animals Animal2 = new Animals("Name2", "Zombie", "", "Can't swim");
        Animals Animal3 = new Animals("Name3", "Navy", "Fireman", "Battleship");
        AnimalList.add(Animal1);
        AnimalList.add(Animal2);
        AnimalList.add(Animal3);
        // Act
        menuService.deleteAnimal(AnimalList);
        // Assert
        assertThat(outputStream.toString(),containsString("Are you sure you want to delete"));
        assertThat(outputStream.toString(),containsString("The animal is safe!"));
        assertThat(3,equalTo(AnimalList.size()));
    }


    @Test
    /**
     * Given a main menu
     * When the exit method is prompted and confirmed yes
     * Then the program gives exit message
     */
    public void exitMethodYesThenExit(){
        // Arrange
        Scanner scanner = new Scanner("Y");
        MenuService menuService = new MenuService(scanner);
        // Act
        menuService.exitAnimal();
        // Assert
        assertThat(outputStream.toString(),containsString("--Exit Animal Shelter--"));
        assertThat(outputStream.toString(),containsString("Are you sure you want to leave so soon? (Y/N)"));
        assertThat(outputStream.toString(),containsString("Good bye! Hope to see you again soon!"));
    }
}