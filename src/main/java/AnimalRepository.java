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
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable");

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

        // success?
        if (countAnimals()>before){
            System.out.println("An animal has successfully been added to the database!");
        } else {
            System.out.println("Nothing happened");
        }
    }


    //read animals by ID #3
    public Animal readAnimalID(int id) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM animaltable WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Animal animalToSee = null;
        while (resultSet.next()) {
            System.out.print("ID:" + resultSet.getInt("id") + ") ");
            Animal animal = new Animal(
                    resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            System.out.printf("NAME: %s   SPECIES: %s   BREED: %s   DESCRIPTION: %s\n",
                    animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());
            animalToSee = animal;
        }
        return animalToSee;
    }


    // edit animal by ID #4
    public void editAnimalID(int id, Animal animal) throws SQLException {
        Statement stmt = conn.createStatement();
        Animal animalInDatabase = readAnimalID(id);
        // Checking if anything is empty
        if (animal.getName().isEmpty()){
            animal.setName(animalInDatabase.getName());
        }
        if (animal.getSpecies().isEmpty()){
            animal.setSpecies(animalInDatabase.getSpecies());
        }
        if (animal.getBreed().isEmpty()){
            animal.setBreed(animalInDatabase.getBreed());
        }
        if (animal.getDescription().isEmpty()){
            animal.setDescription(animalInDatabase.getDescription());
        }
        // Updating the values
        stmt.execute("UPDATE animaltable SET " +
                "name = '" + animal.getName() + "'," +
                "species = '" + animal.getSpecies() + "'," +
                "breed = '" + animal.getBreed() + "'," +
                "description = '" + animal.getDescription() + "'" +
                        "WHERE id = " + id);
    }


    //delete animals #5
    public void deleteAnimal(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM animaltable WHERE id = " + id);
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
