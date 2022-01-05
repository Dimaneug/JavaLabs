package classes;

import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main class, also controller
 * @author Dmitry
 * @version 1.0
 *
 */

public class Main {
    private static String login;

    private static LinkedList<Forest> NewForests(int k) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Forest> forests = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            LinkedList<Animal> tempAnimal = new LinkedList<>();
            LinkedList<Plant> tempPlant = new LinkedList<>();
            int count;
            do {
                View.print("Введите количество хищников:");
                while (!sc.hasNextInt()) {
                    System.out.println("Это не число!");
                    sc.next();
                }
                count = sc.nextInt();
            } while (count < 0);
            for (int j = 0; j < count; j++) {
                int size;
                do {
                    View.print("Введите размер " + (i + 1) + " хищника:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Это не число!");
                        sc.next();
                    }
                    size = sc.nextInt();
                } while (size < 0);
                tempAnimal.add(new Predator(size));
            }
            do {
                View.print("Введите количество травоядных:");
                while (!sc.hasNextInt()) {
                    System.out.println("Это не число!");
                    sc.next();
                }
                count = sc.nextInt();
            } while (count < 0);
            for (int j = 0; j < count; j++) {
                int size;
                do {
                    View.print("Введите размер " + (i + 1) + " травоядного:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Это не число!");
                        sc.next();
                    }
                    size = sc.nextInt();
                } while (size < 0);
                tempAnimal.add(new Herbivore(size));
            }
            do {
                View.print("Введите количество деревьев:");
                while (!sc.hasNextInt()) {
                    System.out.println("Это не число!");
                    sc.next();
                }
                count = sc.nextInt();
            } while (count < 0);
            for (int j = 0; j < count; j++) {
                boolean is_poisonous;
                View.print((i + 1) + " дерево ядовитое? true/false");
                while (!sc.hasNextBoolean()) {
                    System.out.println("Нужно ввести true или false!");
                    sc.next();
                }
                is_poisonous = sc.nextBoolean();
                tempPlant.add(new Tree(is_poisonous));
            }
            do {
                View.print("Введите количество трав:");
                while (!sc.hasNextInt()) {
                    System.out.println("Это не число!");
                    sc.next();
                }
                count = sc.nextInt();
            } while (count < 0);
            for (int j = 0; j < count; j++) {
                boolean is_poisonous;
                View.print((i + 1) + " трава ядовитая? true/false");
                while (!sc.hasNextBoolean()) {
                    System.out.println("Нужно ввести true или false!");
                    sc.next();
                }
                is_poisonous = sc.nextBoolean();

                tempPlant.add(new Tree(is_poisonous));
            }
            forests.add(new Forest(tempAnimal, tempPlant));
        }
        return forests;
    }

    /**
     * Main method
     * @param args - String[]
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        LocalTime time = LocalTime.now();
        LocalDate today = LocalDate.now();
        File f1 = new File("src/log.txt");
        FileWriter log = new FileWriter(f1, true);
        File settings = new File("src/settings.txt");
        if (settings.exists()) {
            System.out.println("Файл существует");
            try {
                FileReader fileReader = new FileReader(settings);
                BufferedReader reader = new BufferedReader(fileReader);
                login = reader.readLine();
                String str = String.format("%s авторизован %s:%s:%s %s.%s.%s\n", login,  time.getHour(),
                        time.getMinute(), time.getSecond(), today.getDayOfMonth(), today.getMonth().getValue(),
                        today.getYear());
                log.write(str);
                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл не существует");
            try {
                FileWriter fileWriter = new FileWriter(settings);
                String login = sc.nextLine();
                fileWriter.write(login);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int key = 1;
        Forest temp = new Forest();
        LinkedList<Forest> forests = temp.fromFile("src/data.txt", log);
        System.out.println(forests);
        while (key != 0) {
            View.menu(login);
            key = sc.nextInt();
            switch (key) {
                case 1:
                    log.write("Выведены данные из файла\n");
                    for (Forest i : forests)
                        View.print(i.toShortString());
                    break;
                case 2:
                    int k;
                    do {
                        View.print("Сколько объектов вы хотите добавить?");
                        while(!sc.hasNextInt()) {
                            System.out.println("Это не число!");
                            sc.next();
                        }
                        k = sc.nextInt();
                    } while(k < 0);
                    forests.addAll(NewForests(k));
                    log.write("Добавлено "+k+" новых объектов\n");
                    break;
                case 3:
                    int index;
                    do {
                        View.print("Какой объект вы хотите изменить?");
                        while(!sc.hasNextInt()) {
                            System.out.println("Это не число!");
                            sc.next();
                        }
                        index = sc.nextInt();
                    } while(index < 0 || index > forests.size()-1);
                    forests.remove(index);
                    forests.addAll(index, NewForests(1));
                    log.write("Изменен "+index+" объект\n");
                    break;
                case 4:
                    do {
                        View.print("Какой объект вы хотите удалить?");
                        while(!sc.hasNextInt()) {
                            System.out.println("Это не число!");
                            sc.next();
                        }
                        index = sc.nextInt();
                    } while(index < 0 || index > forests.size()-1);
                    forests.remove(index);
                    log.write("Удален "+index+" объект\n");
                    break;
                case 5:
                    File data = new File("src/data.txt");
                    data.delete();
                    for (Forest f : forests) {
                        f.toFile("src/data.txt", log);
                    }
                    log.write("Успешно сохранены новые данные!\n");
                    break;
                case 0:
                    break;


            }
        }




        /*Predator bear = new Predator(250);
        Predator tiger = new Predator(180);
        Herbivore giraffe = new Herbivore(520);
        Herbivore rabbit = new Herbivore(60);
        Grass bearsBreech = new Grass(true);
        Grass common = new Grass(false);
        Tree acacia = new Tree(false);
        LinkedList<Animal> tempAnimals = new LinkedList<>(Arrays.asList(bear, tiger, rabbit));
        LinkedList<Plant> tempPlants = new LinkedList<>(Arrays.asList(bearsBreech, common));
        Forest forest1 = new Forest(tempAnimals, tempPlants);
        tempAnimals = new LinkedList<>(Arrays.asList(giraffe, rabbit));
        tempPlants = new LinkedList<>(Arrays.asList(common, acacia));
        Forest forest1 = new Forest(tempAnimals, tempPlants);*/
        log.close();
    }
}
