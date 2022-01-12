package classes;

/**
 * Class represents plant which can be poisonous
 */
public class Plant {
    /** Poisonous field */
    protected boolean isPoisonous;
    Plant() {
        isPoisonous = false;
    }
    Plant(boolean isPoisonous) {
        this.isPoisonous = isPoisonous;
    }

    /**
     * Sets the poison value
     * @param poisonous - poisonous or not
     */
    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
    }

    /**
     * Returns the poison value
     * @return poisonous or not
     */
    public boolean isPoisonous() {
        return isPoisonous;
    }
}
