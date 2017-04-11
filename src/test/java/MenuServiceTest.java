import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    Connection conn;
    String jdbcUrl = "jdbc:h2:mem:animaltable";

    @Before
    public void before() throws SQLException {
        // ability to read outputs
        this.outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(this.outputStream);
        System.setOut(printStream);


        conn = DriverManager.getConnection(jdbcUrl);
        Statement stmt = conn.createStatement();

        stmt.execute("CREATE TABLE animaltable" +
                "(id SERIAL PRIMARY KEY NOT NULL," +
                "name VARCHAR(25) NOT NULL," +
                "species VARCHAR(25) NOT NULL, " +
                "breed VARCHAR(25)," +
                "description VARCHAR(75) NOT NULL);" +
                "CREATE UNIQUE INDEX animaltable_id_uindex ON animaltable (id);");

        // Maybe start with 2 animals
        stmt.execute("INSERT INTO animaltable" +
                "(id,name,species,breed,description)" +
                "VALUES (1,'name1', 'species1', 'breed1','description1')");
        stmt.execute("INSERT INTO animaltable" +
                "(id,name,species,breed,description)" +
                "VALUES (2,'name2', 'species2', 'breed2','description2')");
    }

    @Test
    /**
     * Given a menu service
     * When the user enters a number between 1 - 6
     * Then the number is returned
     */
    public void userEnterNumberAndGetsNumberBack() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1\n2\n3\n4\n5\n6");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
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
    public void whenMainMenuPromptedThenMainMenuDisplayed() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
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
    public void userInputsNonIntegerThenErrorMessageUntilInteger() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("haha\nhehe\n5");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
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
    public void userInputsNotValidIntegerReturnErrorUntilValidInteger() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("8\n-20\n5");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
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
    public void userInputs1AndNoAnimalsThenNoAnimalsListed() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        animalRepository.deleteAnimal(2);
        animalRepository.deleteAnimal(1);
        // Act
        animalRepository.listAnimals();
        // Assert
        assertThat(outputStream.toString(),containsString("--List of Animals--\n" +
                "All the animals have a home! There is currently no animal living in the shelter!\n"));
    }

    @Test
    /**
     * Given a list of 2 animals
     * When the list animal method is called upon
     * Then the animals' name and species are returned
     */
    public void listOf5AnimalsListMethodThenAnimalNameSpeciesReturned() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.listAnimals();
        // Assert
        assertThat(outputStream.toString(),containsString("--List of Animals--"));
        assertThat(outputStream.toString(),containsString("name1"));
        assertThat(outputStream.toString(),containsString("name2"));
        assertThat(outputStream.toString(),containsString("breed1"));
        assertThat(outputStream.toString(),containsString("breed2"));
        assertThat(outputStream.toString(),containsString("species1"));
        assertThat(outputStream.toString(),containsString("species2"));
        assertThat(outputStream.toString(),containsString("description1"));
        assertThat(outputStream.toString(),containsString("description2"));
    }

    @Test
    /**
     * Given two animals
     * When the list method is called
     * Then each animal is indexed
     */
    public void listOfAnimalsListMethodThenAnimalsIndexed() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.listAnimals();
        // Assert
        assertThat(outputStream.toString(), containsString("1"));
        assertThat(outputStream.toString(), containsString("2"));
    }

    @Test
    /**
     * Given the main menu and the user inputs 2 to create animal
     * When an animal is created
     * The list contains the new animal
     */
    public void animalCreatedThenListContainsNewAnimal() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("Bob\nDinosaur\nLong Neck\nShort legs");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.saveAnimal(menuService.createAnAnimal());

        // Assert
        assertThat(animalRepository.countAnimals(),equalTo(3));
    }

    @Test
    /**
     * Given the main menu and the user inputs 2 to create animal
     * When the user does not put input for not nullable fields (name, species, description)
     * Then an error message is shown and a retry
     */
    public void createAnimalNoInputForNotNullableFieldsErrorMessageShownAndRetry() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner(" \n" +
                "Bob\n" +
                "\n" +
                "Dinosaur\n" +
                "\n" +
                "\n" +
                "Short Legs\n").useDelimiter("\n");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        Animal Animal = menuService.createAnAnimal();
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
    public void viewAnimalPromptedThenAnimalDetailsAtParticularIndexShown() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("2");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.readAnimalID(menuService.viewAnimal());
        // Assert
        assertThat(outputStream.toString(),containsString("name2"));
        assertThat(outputStream.toString(),containsString("species2"));
        assertThat(outputStream.toString(),containsString("breed2"));
        assertThat(outputStream.toString(),containsString("description2"));
    }

    @Test
    /**
     * Given the main menu and the user inputs 3 to view animals
     * When there are no animals
     * Then no animals are listed
     */
    public void userInputs3AndNoAnimalsThenNoAnimalsListed() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("3");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        animalRepository.deleteAnimal(2);
        animalRepository.deleteAnimal(1);
        // Act
        animalRepository.readAnimalID(menuService.viewAnimal());
        // Assert
        assertThat(outputStream.toString(),containsString("--View an Animal--\n" +
                "There are no animals to view!\n"));
    }

    @Test
    /**
     * Given a main menu with 2 animals
     * When bad inputs are given for view animal
     * Then error messages are prompted until valid inputs are inserted
     */
    public void badInputsGivenForViewAnimalThenErrorMessageUntilValidInput() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("5\nDinosaur\n0\n1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        menuService.viewAnimal();
        // Assert
        assertThat(outputStream.toString(),containsString("5 is not part of the list! Please try again!"));
        assertThat(outputStream.toString(),containsString("Error: Invalid input! Dinosaur is not an integer!"));
        assertThat(outputStream.toString(),containsString("0 is not part of the list! Please try again!"));
    }


    @Test
    /**
     * Given a main menu
     * When edit animal is prompted
     * Then edit animal messages come out
     */
    public void EditAnimalIsPromptedThenEditMessages() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("1\nnope\nyup\nhappy\nsad");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        int number = menuService.editAnimalNumber();
        menuService.editAnimalInputs(number);
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
     * Given a main menu with two animals
     * When edit animal method is invoked and bad inputs are given
     * Then error messages are shown
     */
    public void BadInputsGivenThenErrorMessagesShown() throws SQLException {
        Scanner scanner = new Scanner("4\n0\n1\nZoro the swordsman\n123\n \nCaptain").useDelimiter("\n");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        int number = menuService.editAnimalNumber();
        Animal animal = menuService.editAnimalInputs(number);
        animalRepository.editAnimalID(number,animal);
        // Assert
        assertThat(outputStream.toString(),containsString("4 is not a valid option. Please try again!"));
        assertThat(outputStream.toString(),containsString("0 is not a valid option. Please try again!"));
        assertThat(animalRepository.readAnimalID(1).get(0).getName(),equalTo("Zoro the swordsman"));
        assertThat(animalRepository.readAnimalID(1).get(0).getSpecies(),equalTo("123"));
        assertThat(animalRepository.readAnimalID(1).get(0).getBreed(),equalTo("breed1"));
        assertThat(animalRepository.readAnimalID(1).get(0).getDescription(),equalTo("Captain"));
    }

    @Test
    /**
     * Given a main menu
     * When there is no animal in the list for editing (delete animals first)
     * Then it displayed error message and exits
     */
    public void noAnimalInListForEditingThenErrorMessageAndExit() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("2\nyes\n1\nyes\n1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
            // Deleting the two animals inside database
        animalRepository.deleteAnimal(menuService.deleteAnimal());
        animalRepository.deleteAnimal(menuService.deleteAnimal());

        menuService.editAnimalNumber();

        // Assert
        assertThat(outputStream.toString(),containsString("There are no animals! Create an animal first!"));
    }


    @Test
    /**
     * Given a main menu with 2 animals
     * When delete method is prompted 3 times
     * Then a delete message is shown
     */
    public void deleteMethodPromptedThenDeleteMessageShown() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("2\nyes\n1\nyes\n1");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.deleteAnimal(menuService.deleteAnimal());
        animalRepository.deleteAnimal(menuService.deleteAnimal());
        animalRepository.deleteAnimal(menuService.deleteAnimal());
        // Assert
        assertThat(outputStream.toString(),containsString("--Delete an Animal--"));
        assertThat(outputStream.toString(),containsString("There are no animals in the shelter to delete!"));
    }

    @Test
    /**
     * Given a main menu with two animals
     * When the delete method is prompted to delete animal 2 and confirmed yes
     * Then the list has one animal remaining
     */
    public void threeAnimalsDeleteToTwoRemaining() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("2\nYes");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        animalRepository.deleteAnimal(menuService.deleteAnimal());
        // Assert
        assertThat(outputStream.toString(),containsString("Are you sure you want to delete"));
        assertThat(outputStream.toString(),containsString("An animal has been deleted"));
        assertThat(1,equalTo(animalRepository.countAnimals()));
    }

    @Test
    /**
     * Given a main menu with two animals
     * When the delete method is prompted to delete animal 2 and confirmed no
     * Then the list still has two animals remaining
     */
    public void twoAnimalsDeleteStillTwoRemaining() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("2\nNo");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);

        // Act
        animalRepository.deleteAnimal(menuService.deleteAnimal());

        // Assert
        assertThat(outputStream.toString(),containsString("Are you sure you want to delete"));
        assertThat(outputStream.toString(),containsString("The animal is safe!"));
        assertThat(2,equalTo(animalRepository.countAnimals()));
    }


    @Test
    /**
     * Given a main menu
     * When the exit method is prompted and confirmed yes
     * Then the program gives exit message
     */
    public void exitMethodYesThenExit() throws SQLException {
        // Arrange
        Scanner scanner = new Scanner("Y");
        String jdbcUrl = "jdbc:h2:mem:animaltable";
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        MenuService menuService = new MenuService(scanner, animalRepository);
        // Act
        menuService.exitAnimal();
        // Assert
        assertThat(outputStream.toString(),containsString("--Exit Animal Shelter--"));
        assertThat(outputStream.toString(),containsString("Are you sure you want to leave so soon? (Y/N)"));
        assertThat(outputStream.toString(),containsString("Good bye! Hope to see you again soon!"));
    }

    @After
    public void after() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("SHUTDOWN");
    }
}
