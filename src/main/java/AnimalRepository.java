import com.sun.xml.internal.bind.XmlAccessorFactory;

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
        stmt.execute("INSERT INTO animaltable(name,species,breed,description) " +
               "VALUES ('animal.getName()', " +
               "'animal.getSpecies()', " +
               "'animal.getBreed()'," +
               "'animal.getDescription()')");
    }

    //counting animals in table
    public int countAnimals() throws SQLException {
        Statement stmt = conn.createStatement();
        int x = 0;
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable");
        while (resultSet.next()){
            x=x+1;
        }
        return x;
    }

    //delete animals
    public void deleteAnimal(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM animaltable WHERE id = " + id);
    }

    //read animals by ID
    public String readAnimalID(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable WHERE id = "+ id);
        return resultSet.toString();
    }
}
