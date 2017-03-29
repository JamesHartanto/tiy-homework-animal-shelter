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

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getDescription() {
        return description;
    }
}
