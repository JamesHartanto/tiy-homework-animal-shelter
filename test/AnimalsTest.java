import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by JamesHartanto on 3/29/17.
 */
public class AnimalsTest {
    @Test
    /**
     * Given an animal
     * When a name, breed, species, description is inserted
     * Then the properties of the animal is set
     */
    public void animalPropertiesInsertedAndSet(){
        // Arrange
        Animals animals = new Animals("","","","");
        // Act
        animals.setName("Bob");
        animals.setSpecies("Dinosaur?");
        animals.setBreed("Unknown");
        animals.setDescription("Short and chubby");
        // Assert
        assertThat(animals.getName(),equalTo("Bob"));
        assertThat(animals.getSpecies(),equalTo("Dinosaur?"));
        assertThat(animals.getBreed(),equalTo("Unknown"));
        assertThat(animals.getDescription(),equalTo("Short and chubby"));
    }

}
