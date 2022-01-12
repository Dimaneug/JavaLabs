package classes;

/**
 * Class for grass :)
 */
public class Grass extends Plant{
    Grass() {
        super();
    }
    Grass(boolean isPoisonous) {
        super(isPoisonous);
    }
    public String toString() {
        return "Grass "+isPoisonous;
    }
}
