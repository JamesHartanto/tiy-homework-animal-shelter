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

    // list animals #1
    public ArrayList<Animals> listAnimals() throws SQLException {
        ArrayList<Animals> animalsArrayList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable");

        while (resultSet.next()){
            System.out.print("ID:" + resultSet.getInt("id") + ") ");
            Animals animal = new Animals(
                    resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            System.out.printf("NAME: %s   SPECIES: %s   BREED: %s   DESCRIPTION: %s\n",
                    animal.getName(),animal.getSpecies(),animal.getBreed(),animal.getDescription());
            animalsArrayList.add(animal);
        }

        if (animalsArrayList.size()==0){
            System.out.println("All the animals have a home! " +
                    "There is currently no animal living in the shelter!");
        }
        return animalsArrayList;
    }


    // sending data to database table #2
    public void saveAnimal(Animals animal) throws SQLException {
        // before
        int before = countAnimals();
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO animaltable(name,species,breed,description) " +
               "VALUES ('" + animal.getName() + "', '" +
               animal.getSpecies()+"', '" +
               animal.getBreed()+"','" +
               animal.getDescription()+"')");
        // success?
        if (countAnimals()>before){
            System.out.println("An animal has successfully been added to the database!");
        } else {
            System.out.println("Nothing happened");
        }
    }


    //read animals by ID #3
    public Animals readAnimalID(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable WHERE id = "+ id);
        resultSet.next();
        Animals animal = new Animals(resultSet.getString("name"),
                resultSet.getString("species"),
                resultSet.getString("breed"),
                resultSet.getString("description"));
        return animal;
    }


    // edit animal by ID #4
    public void editAnimalID(int id, Animals animal) throws SQLException {
        Statement stmt = conn.createStatement();
        Animals animalInDatabase = readAnimalID(id);
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


    public ArrayList<Animals> filterBy(String tableColumn, String value) throws SQLException {
        ArrayList<Animals> animalsArrayList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM animaltable WHERE '" + tableColumn + "' = '" + value + "'");

        while (resultSet.next()){
            Animals animal = new Animals(resultSet.getString("name"),
                    resultSet.getString("species"),
                    resultSet.getString("breed"),
                    resultSet.getString("description"));
            animalsArrayList.add(animal);
        }
        return animalsArrayList;
    }
}
