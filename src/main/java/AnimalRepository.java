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

    // list animals #1
    public ArrayList<Animal> listAnimals() throws SQLException {
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable ORDER BY id ASC");

        while (resultSet.next()){
            Animal animal = new Animal(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            animalArrayList.add(animal);
        }
        return animalArrayList;
    }


    // sending data to database table #2
    public void saveAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO animaltable(name,species,breed,description) " +
               "VALUES (?,?,?,?)");
        preparedStatement.setString(1,animal.getName());
        preparedStatement.setString(2,animal.getSpecies());
        preparedStatement.setString(3,animal.getBreed());
        preparedStatement.setString(4,animal.getDescription());

        preparedStatement.execute();
    }


    //read animals by ID #3
    public Animal readAnimalID(int id) throws SQLException {
        Animal animal = null;
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM animaltable WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            animal = new Animal(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
        }
        return animal;
    }


    // edit animal by ID #4
    public void editAnimalID(Animal animal) throws SQLException {
        // Updating the values
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE animaltable SET " +
                "name = ?," +
                "species = ?," +
                "breed = ?," +
                "description = ?" +
                "WHERE id = ?");

        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getSpecies());
        preparedStatement.setString(3, animal.getBreed());
        preparedStatement.setString(4, animal.getDescription());
        preparedStatement.setInt(5, animal.getId());

        preparedStatement.execute();
    }


    //delete animals #5
    public void deleteAnimal(int id) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM animaltable WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
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
}
