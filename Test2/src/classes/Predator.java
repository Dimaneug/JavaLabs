package classes;

import java.util.LinkedList;

/**
 * Class represents predator
 */
public class Predator extends Animal{
    Predator(){
        super();
    }
    Predator(int size) {
        super(size);
    }

    /**
     * Method for searching food for predator.
     * Predators can eat only animals.
     * @param forest - forest to search in
     */
    @Override
    public void searchForFood(Forest forest) {
        for (Animal animal: forest.getAnimals()) {
            // Predator can eat animal if it's smaller than it.
            if (animal.getSize() < size) {
                LinkedList<Animal> temp = forest.getAnimals();
                temp.remove(animal);
                forest.setAnimals(temp);
                System.out.println("Хищник съел животное");
                return;
            }
        }
        System.out.println("Хищник не нашел подохдящюю еду");
    }
    public String toString() {
        return "Predator "+size;
    }
}
