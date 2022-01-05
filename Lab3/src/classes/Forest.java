package classes;

import java.io.*;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Class represents forest which contains animals and plants
 */

public class Forest {
    private LinkedList<Animal> animals;
    private LinkedList<Plant> plants;

    public Forest(LinkedList<Animal> animals, LinkedList<Plant> plants) {
        this.animals = animals;
        this.plants = plants;
    }

    public Forest() {

    }

    /**
     * Setting LinkedList with animals
     * @param animals - animals
     */
    public void setAnimals(LinkedList<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Setting LinkedList with plants
     * @param plants - plants
     */
    public void setPlants(LinkedList<Plant> plants) {
        this.plants = plants;
    }

    /**
     * Returns LinkedList with animals
     * @return animals
     */
    public LinkedList<Animal> getAnimals() {
        return animals;
    }

    /**
     * Returns LinkedList with plants
     * @return plants
     */
    public LinkedList<Plant> getPlants() {
        return plants;
    }

    String toShortString() {
        StringBuilder str = new StringBuilder();
        for (Animal i : animals)
            str.append(i.toString()).append(", ");
        str.append("\n");
        for (Plant i : plants)
            str.append(i.toString()).append(", ");
        return str + "\n";
    }
    public void toFile(String path, FileWriter log){
        try(
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path,true))
        ) {

            log.write("Попытка записи в файл "+path+"\n");

            writer.write(this.toShortString()+"\n");
            writer.flush();
        }
        catch(IOException e){
            try {
                log.write("Не удалось записать в файл! "+e+"\n");
            } catch (IOException ex) {
                System.out.println("Не удалось записать лог!");
            }
        }
    }
    public LinkedList<Forest> fromFile(String path, FileWriter log){
        LinkedList<Forest> forests = new LinkedList<Forest>();
        try {
            log.write("Чтение из файла "+path+" "+ LocalTime.now()+"\n");
        } catch (IOException e) {
            System.out.println("Не удалось записать лог!");
        }
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(", ");
                LinkedList<Animal> animals = new LinkedList<Animal>();
                for(String j : data){
                    String[] k = j.split(" ");
                    if (k[0].equals("Predator"))
                        animals.add(new Predator(Integer.parseInt(k[1])));
                    else
                        animals.add(new Herbivore(Integer.parseInt(k[1])));
                }
                LinkedList<Plant> plants = new LinkedList<Plant>();
                data = br.readLine().split(", ");
                for(String j: data) {
                    String[] k = j.split(" ");
                    if (k[0].equals("Grass"))
                        plants.add(new Grass(Boolean.parseBoolean(k[1])));
                    else
                        plants.add(new Tree(Boolean.parseBoolean(k[1])));
                }
                forests.add(new Forest(animals, plants));
                br.readLine();
            }
        }
        catch (IOException | RuntimeException e) {
            try {
                log.write("Не удалось прочитать из файла "+e+" !"+LocalTime.now()+"\n");
            } catch (IOException ex) {
                System.out.println("Не удалось записать лог!");
            }
        }
        return forests;
    }
    /*public int hashCode(){
        int hash = 1;
        for (IngredientMass i: Ingredients)
            hash*=i.hashCode();
        for (Cooking i: HowToCook)
            hash*=i.hashCode();
        return hash;
    }*/
}
