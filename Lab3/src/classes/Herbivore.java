package classes;

import java.util.LinkedList;

/**
 * Class represents herbivore
 */
public class Herbivore extends Animal{
    Herbivore(int size) {
        super(size);
    }

    /**
     * Method for searching food for herbivore.
     * Herbivore can eat only plants.
     * @param forest - forest to search in
     */
    @Override
    public void searchForFood(Forest forest) {
        for (Plant plant : forest.getPlants()) {
            //Herbivore can eat plant if it's not poisonous.
            if (!plant.isPoisonous()) {
                LinkedList<Plant> temp = forest.getPlants();
                temp.remove(plant);
                forest.setPlants(temp);
                System.out.println("Травоядный съел растение");
                return;
            }
        }
        System.out.println("Травоядный не нашел подходящую еду");
    }
    public String toString() {
        return "Herbivore "+size;
    }
}
