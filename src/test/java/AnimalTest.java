import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by JamesHartanto on 3/29/17.
 */
public class AnimalTest {
    @Test
    /**
     * Given an animal
     * When a name, breed, species, description is inserted
     * Then the properties of the animal is set
     */
    public void animalPropertiesInsertedAndSet(){
        // Arrange
        Animal animal = new Animal("","","","");
        // Act
        animal.setName("Bob");
        animal.setSpecies("Dinosaur?");
        animal.setBreed("Unknown");
        animal.setDescription("Short and chubby");
        // Assert
        assertThat(animal.getName(),equalTo("Bob"));
        assertThat(animal.getSpecies(),equalTo("Dinosaur?"));
        assertThat(animal.getBreed(),equalTo("Unknown"));
        assertThat(animal.getDescription(),equalTo("Short and chubby"));
        assertThat(animal.toString(),containsString("Animal{"));
    }

    @Test
    /**
     * Given an animal
     * When a id, name, breed, species, description is inserted
     * Then the properties of the animal is set
     */
    public void allAnimalPropertiesInsertedAndSet(){
        // Arrange
        Animal animal = new Animal(1,"","","","");
        // Act
        animal.setId(2);
        animal.setName("Bob");
        animal.setSpecies("Dinosaur?");
        animal.setBreed("Unknown");
        animal.setDescription("Short and chubby");
        // Assert
        assertThat(animal.getId(),equalTo(2));
        assertThat(animal.getName(),equalTo("Bob"));
        assertThat(animal.getSpecies(),equalTo("Dinosaur?"));
        assertThat(animal.getBreed(),equalTo("Unknown"));
        assertThat(animal.getDescription(),equalTo("Short and chubby"));
        assertThat(animal.toString(),containsString("Animal{"));
    }

    @Test
    /**
     * Given an animal
     * When a id, name, breed, species, description is inserted
     * Then the properties of the animal is set
     */
    public void zeroAnimalPropertiesInsertedAndSet(){
        // Arrange
        Animal animal = new Animal();
        // Act
        animal.setId(2);
        animal.setName("Bob");
        animal.setSpecies("Dinosaur?");
        animal.setBreed("Unknown");
        animal.setDescription("Short and chubby");
        // Assert
        assertThat(animal.getId(),equalTo(2));
        assertThat(animal.getName(),equalTo("Bob"));
        assertThat(animal.getSpecies(),equalTo("Dinosaur?"));
        assertThat(animal.getBreed(),equalTo("Unknown"));
        assertThat(animal.getDescription(),equalTo("Short and chubby"));
        assertThat(animal.toString(),containsString("Animal{"));
    }
}
