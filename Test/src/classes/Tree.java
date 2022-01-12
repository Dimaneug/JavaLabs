package classes;

/**
 * Class for tree
 */
public class Tree extends Plant{
    Tree() {
        super();
    }
    Tree(boolean isPoisonous) {
        super(isPoisonous);
    }
    public String toString() {
        return "Tree "+isPoisonous;
    }
}
