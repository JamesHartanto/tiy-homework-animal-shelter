/**
 * Created by JamesHartanto on 3/29/17.
 */
public class Animals {

    private String name;
    private String species;
    private String breed;
    private String description;

    public Animals(String name, String species, String breed, String description){
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    // SETTERS
    // Name
    public void setName(String name) {
        this.name = name;
    }

    // Species
    public void setSpecies(String species) {
        this.species = species;
    }

    // Breed
    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Description
    public void setDescription(String description) {
        this.description = description;
    }

    // GETTERS
    // Name
    public String getName() {
        return name;
    }

    // Species
    public String getSpecies() {
        return species;
    }

    // Breed
    public String getBreed() {
        return breed;
    }

    // Desciption
    public String getDescription() {
        return description;
    }
}
