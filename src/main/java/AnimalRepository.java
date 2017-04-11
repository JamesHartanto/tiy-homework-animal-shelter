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
        System.out.println("--List of Animals--");
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable ORDER BY id ASC");

        if (countAnimals() == 0){
            System.out.println("All the animals have a home! There is currently no animal living in the shelter!");
        }
        while (resultSet.next()){
            System.out.print("ID:" + resultSet.getInt("id") + ") ");
            Animal animal = new Animal(
                    resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            System.out.printf("NAME: %s   SPECIES: %s   BREED: %s   DESCRIPTION: %s\n",
                    animal.getName(),animal.getSpecies(),animal.getBreed(),animal.getDescription());
            animalArrayList.add(animal);
        }
        return animalArrayList;
    }


    // sending data to database table #2
    public void saveAnimal(Animal animal) throws SQLException {
        // before
        int before = countAnimals();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO animaltable(name,species,breed,description) " +
               "VALUES (?,?,?,?)");
        preparedStatement.setString(1,animal.getName());
        preparedStatement.setString(2,animal.getSpecies());
        preparedStatement.setString(3,animal.getBreed());
        preparedStatement.setString(4,animal.getDescription());

        preparedStatement.execute();

        // success?
        if (countAnimals()>before){
            System.out.println("An animal has successfully been added to the database!");
        } else {
            System.out.println("Nothing happened");
        }
    }


    //read animals by ID #3
    public ArrayList<Animal> readAnimalID(int id) throws SQLException {
        ArrayList<Animal> animalList = new ArrayList<>();
        if (id != 0) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM animaltable WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print("ID:" + resultSet.getInt("id") + ") ");
                Animal animal = new Animal(
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        resultSet.getString("breed"),
                        resultSet.getString("description"));
                System.out.printf("NAME: %s   SPECIES: %s   BREED: %s   DESCRIPTION: %s\n",
                        animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());
                animalList.add(animal);
            }
        }
        return animalList;

    }


    // edit animal by ID #4
    public void editAnimalID(int id, Animal animal) throws SQLException {
        if (id != 0) {
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
            preparedStatement.setInt(5, id);

            preparedStatement.execute();
            System.out.println("Animal has been successfully updated!");
        }
    }


    //delete animals #5
    public void deleteAnimal(int id) throws SQLException {
        int before = countAnimals();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM animaltable WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        int after = countAnimals();
        if (before > after) {
            System.out.println("An animal has been deleted!");
        }
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


    public ArrayList<Animal> filterBy(String tableColumn, String value) throws SQLException {
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable WHERE '" + tableColumn + "' = '" + value + "'");

        while (resultSet.next()){
            Animal animal = new Animal(resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            animalArrayList.add(animal);
        }
        return animalArrayList;
    }
}
