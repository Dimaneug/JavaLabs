package classes;

/**
 * Class represents animal with size
 */
public abstract class Animal {
    /** size field */
    protected int size;

    Animal() {
        size = 0;
    }
    Animal(int size) {
        this.size = size;
    }

    /**
     * Setting animal size
     * @param size - animal size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Returns animal size
     * @return animal size
     */
    public int getSize() {
        return size;
    }

    /**
     * Method for finding food for animal in a forest
     * @param forest - forest to search in
     */
    public abstract void searchForFood(Forest forest);

}
