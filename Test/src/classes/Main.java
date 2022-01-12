package classes;

import java.io.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

    private static final Logger Log = Logger.getLogger(Main.class.getName());
    private static final WriteInLog mylog = new WriteInLog();
    private static void handle(){
        FileHandler handler=null;
        try {
            handler = new FileHandler("log.log",true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        assert handler != null;
        handler.setFormatter(new SimpleFormatter());
        Log.addHandler(handler);
    }

    /**
     * Main method
     * @param args - String[]
     */
    public static void main(String[] args)  {
        handle();
        Scanner sc = new Scanner(System.in);
        LocalTime time = LocalTime.now();
        LocalDate today = LocalDate.now();
        File settings = new File("src/settings.txt");
        if (settings.exists()) {
            System.out.println("Файл существует");
            try {
                FileReader fileReader = new FileReader(settings);
                BufferedReader reader = new BufferedReader(fileReader);
                login = reader.readLine();
                String str = String.format("%s авторизован %s:%s:%s %s.%s.%s", login,  time.getHour(),
                        time.getMinute(), time.getSecond(), today.getDayOfMonth(), today.getMonth().getValue(),
                        today.getYear());
                mylog.addInLog(Log, str);
                reader.close();

            } catch (IOException e) {
                mylog.addErrWithLog(Log, e);
                mylog.showErrText(e);
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
        LinkedList<Forest> forests = temp.fromFile("src/data.txt", Log);
        System.out.println(forests);
        while (key != 0) {
            View.menu(login);
            key = sc.nextInt();
            switch (key) {
                case 1:
                    mylog.addInLog(Log, "Выведены данные из файла");
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
                    mylog.addInLog(Log, "Добавлено "+k+" новых объектов");
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
                    mylog.addInLog(Log, "Изменен "+index+" объект");
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
                    mylog.addInLog(Log, "Удален "+index+" объект");
                    break;
                case 5:
                    File data = new File("src/data.txt");
                    data.delete();
                    for (Forest f : forests) {
                        f.toFile("src/data.txt", Log);
                    }
                    mylog.addInLog(Log, "Успешно сохранены новые данные!");
                    break;
                case 0:
                    break;


            }

        }
        ForestArray f1 = new ForestArray(10);
		f1.resize();
        ForestArray f2 = new ForestArray(100);
        ForestArray f3 = new ForestArray(1000);
        ForestArray f4 = new ForestArray(10000);
        ForestArray f5 = new ForestArray(100000);
        ForestArray.toFile(Log);
    }
}
