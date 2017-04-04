import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by JamesHartanto on 4/3/17.
 */
public class AnimalRepositoryTest {

    private Connection conn;
    private String jdbcUrl = "jdbc:h2:mem:animaltable";

    @Before
    public void before() throws SQLException {

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
     * Given an Animal Repository with 2 animals
     * Then their name, species, breed, and description can get extracted
     */
    // need to use next method to go through data
    public void animalRepositoryWith2AnimalsGetAnimalDetails() throws SQLException {
        // Arrange
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable");
        // Creating an arraylist of Animals to store the outputs
        ArrayList<Animals> animalsArrayList = new ArrayList<>();
        while (resultSet.next()){
            Animals animal = new Animals(resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            animalsArrayList.add(animal);
        }
        // Assert
        assertThat(animalsArrayList.size(),equalTo(2));
    }


    @Test
    /**
     * Given an Animal Repository with 2 animals
     * When create animal method is invoked
     * Then the animal is added to the database table and there is 3 animals
     */
    public void createAnimalMethodThenAnimalAddedToDatabaseTable() throws SQLException {
        // Arrange
        AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
        Animals animal1 = new Animals("Bob","Dinosaur","T-rex","Short arms");

// look up count method
        // Act
        animalRepository.saveAnimal(animal1);
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable");

        // Creating an arraylist of Animals to store the outputs
        ArrayList<Animals> animalsArrayList = new ArrayList<>();
        while (resultSet.next()){
            Animals animal = new Animals(resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            animalsArrayList.add(animal);
        }
        // Assert
        assertThat(3,equalTo((animalsArrayList.size())));
    }

    @After
    public void after() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("SHUTDOWN");
    }

}
