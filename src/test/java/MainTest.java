/**
 * Created by JamesHartanto on 4/4/17.
 */
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by JamesHartanto on 4/1/17.
 */
public class MainTest {
    ByteArrayOutputStream outputStream;

    @Before
    public void before(){
        this.outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(this.outputStream);
        System.setOut(printStream);
    }

    @Test
    /**
     * Given a main menu with no animals
     * When the user inputs 1
     * Then it lists the animals
     */
    public void whenUserInputs1ToListNoAnimals() throws SQLException {
        // Arrange
        Main.scanner = new Scanner("1\n6\nYes");
        // Act
        Main.main(null);
        // Assert
        assertThat(outputStream.toString(),containsString("--List of Animals--"));
    }

    @Test
    /**
     * Given a main menu with no animals
     * When the user inputs 2
     * Then it creates an animal
     */
    public void whenUserInputs2ToCreateAnimal() throws SQLException {
        // Arrange
        Main.scanner = new Scanner("2\nBob\nBill\n \nNothing\n6\nYes");
        // Act
        Main.main(null);
        // Assert
        assertThat(outputStream.toString(),containsString("--Create an Animal--"));
    }

    @Test
    /**
     * Given a main menu with no animals
     * When the user inputs 3
     * Then it views no animal
     */
    public void whenUserInputs3ToViewNoAnimal() throws SQLException {
        // Arrange
        Main.scanner = new Scanner("3\n6\nYes");
        // Act
        Main.main(null);
        // Assert
        assertThat(outputStream.toString(),containsString("--View an Animal--"));
    }

    @Test
    /**
     * Given a main menu with no animals
     * When the user inputs 4
     * Then it edits no animal
     */
    public void whenUserInputs4ToEditNoAnimal() throws SQLException {
        // Arrange
        Main.scanner = new Scanner("4\n6\nYes");
        // Act
        Main.main(null);
        // Assert
        assertThat(outputStream.toString(),containsString("--Edit Animal--"));
    }

    @Test
    /**
     * Given a main menu with no animals
     * When the user inputs 5
     * Then it deletes no animal
     */
    public void whenUserInputs5ToDeleteNoAnimal() throws SQLException {
        // Arrange
        Main.scanner = new Scanner("5\n6\nYes");
        // Act
        Main.main(null);
        // Assert
        assertThat(outputStream.toString(),containsString("--Delete an Animal--"));
    }
}