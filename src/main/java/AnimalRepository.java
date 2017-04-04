import java.sql.*;
import java.util.ArrayList;

/**
 * Created by JamesHartanto on 4/3/17.
 */
public class AnimalRepository {

    private final Connection conn;

    public AnimalRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    // sending data to database table
    public void saveAnimal(Animals animal) throws SQLException {
        Statement stmt = conn.createStatement();
       stmt.execute("INSERT INTO animalList(name,species,breed,description) " +
                "VALUE (animal.getName(), " +
               "animal.getSpecies(), " +
               "animal.getBreed()," +
               "animal.getDescription())");
    }

    //read animals by ID
    public void readAnimalID(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animallist WHERE id = "+ id);
    }

    //delete animals
    public void deleteAnimal(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM animallist WHERE id = " + id);
    }
}
