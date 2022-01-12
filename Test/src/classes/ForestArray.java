package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ForestArray {
    private final ArrayList<Forest> ArrayListForests;
    private final HashMap<Integer, Forest> HashMapForests;
    private final int Amount;
    private static final WriteInLog mylog = new WriteInLog();
    private final Logger Log1;
    private final Logger Log2;
    private void handle(){
        FileHandler handler1=null;
        FileHandler handler2=null;
        try {
            handler1 = new FileHandler("ArrayList"+Amount+".log",true);
            handler2 = new FileHandler("HashMap"+Amount+".log",true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        assert handler1 != null;
        handler1.setFormatter(new SimpleFormatter());
        assert handler2 != null;
        handler2.setFormatter(new SimpleFormatter());
        Log1.addHandler(handler1);
        Log2.addHandler(handler2);
    }


    private static int Count = 0;
    public static long[][] Added = new long[2][5];
    public static long[][] Removed = new long[2][5];
    public ForestArray(int Amount) {

        LocalTime time = LocalTime.now();
        LocalDate today = LocalDate.now();
        ArrayListForests = new ArrayList<Forest>(Amount);
        HashMapForests = new HashMap<Integer, Forest>(Amount);
        Log1=Logger.getLogger(Main.class.getName()+1+Amount);
        Log2=Logger.getLogger(Main.class.getName()+2+Amount);
        this.Amount = Amount;
        this.handle();
        mylog.addInLog(Log1, "Start program: {0} {1}", new Object[] {today.toString(),
                time.format(DateTimeFormatter.ofPattern("H:mm:ss"))});
        mylog.addInLog(Log2, "Start program: {0} {1}", new Object[] {today.toString(),
                time.format(DateTimeFormatter.ofPattern("H:mm:ss"))});
        mylog.addInLog(Log1,"ArrayList");
        mylog.addInLog(Log2, "HashMap");
        add();
        remove();
        Count++;
    }
    private void add() {
        long time, allTime1=0, allTime2=0;
        for(int i = 0; i < Amount; i++) {
            Forest New = RandomForest();

            time = System.nanoTime();
            ArrayListForests.add(New);
            time = System.nanoTime() - time;
            mylog.addInLog(Log1, "add, ID={0}, {1}", new Object[] {i, time});
            allTime1+=time;

            time = System.nanoTime();
            HashMapForests.put(i, New);
            time = System.nanoTime() - time;
            allTime2+=time;
            mylog.addInLog(Log2,"add, ID={0}, {1}", new Object[] {i, time});
        }
        mylog.addInLog(Log1,"addTotalCount = " + Amount);
        mylog.addInLog(Log1,"addTotalTime = {0}" + allTime1);
        mylog.addInLog(Log1,"addMedianTime = {0}" + allTime1/Amount);
        Added[0][Count]=allTime1/Amount;
        mylog.addInLog(Log2,"addTotalCount = " + Amount);
        mylog.addInLog(Log2,"addTotalTime = {0}" + allTime1);
        mylog.addInLog(Log2,"addMedianTime = {0}" + allTime1/Amount);
        Added[1][Count]=allTime2/Amount;

    }
    private void remove() {
        long time, allTime2=0, allTime1=0;
        int part = Amount/10;
        for(int i = Amount - 1; i > Amount - 1 - part; i--) {
            time = System.nanoTime();
            ArrayListForests.remove(i);
            time = System.nanoTime() - time;
            Log1.log(Level.INFO, "remove, ID={0}, {1}", new Object[]{i, time});
            allTime1+=time;

            time = System.nanoTime();
            HashMapForests.remove(i);
            time = System.nanoTime() - time;
            allTime2+=time;
            Log2.log(Level.INFO, "remove, ID={0}, {1}", new Object[] {i, time});
        }
        Log1.log(Level.INFO, "removeTotalCount = {0}", part);
        Log1.log(Level.INFO, "removeTotalTime = {0}", allTime1);
        Log1.log(Level.INFO, "removeMedianTime = {0}", allTime1/part);
        Removed[0][Count]=allTime1/Amount;
        Log2.log(Level.INFO, "\nremoveTotalCount = {0}", part);
        Log2.log(Level.INFO, "removeTotalTime = {0}", allTime2);
        Log2.log(Level.INFO, "removeMedianTime = {0}", allTime2/part);
        Removed[1][Count]=allTime2/Amount;
    }
    private Forest RandomForest(){
        LinkedList<Animal> animals = new LinkedList<>();
        animals.add(new Predator(20));
        animals.add(new Herbivore(30));
        LinkedList<Plant> plants = new LinkedList<>();
        plants.add(new Tree(true));
        plants.add(new Grass(false));
        return new Forest(animals, plants);
    }
    public void resize(){
        int newSize = Amount*3/2+1;
        ArrayListForests.ensureCapacity(newSize);
        newSize-=Amount;
        long time,allTime1=0;
        for(int i=0; i<newSize; i++){
            Forest New = RandomForest();

            time = System.nanoTime();
            ArrayListForests.add(New);
            time = System.nanoTime() - time;
            mylog.addInLog(Log1, "addAfterResize, ID={0}, {1}", new Object[] {i, time});
            allTime1+=time;
        }
        mylog.addInLog(Log1, "medianResizeTime = {0}" + allTime1/newSize);

    }
    public static void toFile(Logger log){
        try {
            Files.delete(Paths.get("Add.txt"));
        } catch (IOException e) {
            mylog.addErrWithLog(log, e);
            mylog.showErrText(e);
        }
        try(FileWriter writer = new FileWriter("Add.txt", true))
        {
            for(int i = 0; i<5; i++)
                writer.write(Added[0][i]+" "+ Added[1][i]+"\n");
        }
        catch(IOException e){
            mylog.addErrWithLog(log, e);
            mylog.showErrText(e);
        }
        try {
            Files.delete(Paths.get("Remove.txt"));
        } catch (IOException e) {
            mylog.addErrWithLog(log, e);
            mylog.showErrText(e);
        }
        try(FileWriter writer = new FileWriter("Remove.txt", true))
        {

            for(int i = 0; i<5; i++)
                writer.write(Removed[0][i]+" "+ Removed[1][i]+"\n");
        }
        catch(IOException e){
            mylog.addErrWithLog(log, e);
            mylog.showErrText(e);
        }
    }

}
