package classes;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteInLog {
    private static ArrayList<Exception> ErrList;
    public WriteInLog() {
        ErrList = new ArrayList<>();
    }
    public void addErr(Exception e) {
        ErrList.add(e);
    }
    public void addErrWithLog(Logger log, Exception e) {
        if (log != null)  {
            ErrList.add(e);
            log.log(Level.WARNING, e.getMessage());
        }
        else {
            System.out.println("Logger не должен быть null!");
        }

    }
    public int getErrCount() {
        return ErrList.size();
    }
    public void showErrText(Exception e) {
        System.err.println(e.getMessage());
    }
    public Exception makeErr(Exception e) {
        addErr(e);
        return new Exception(e);
    }
    public void addInLog(Logger log, String str) {
        if (log != null){
            log.log(Level.INFO, str);
        }
        else {
            System.out.println("Logger не должен быть null!");
        }

    }
    public void addInLog(Logger log, String str, Object [] obj) {
        if (log != null) {
            log.log(Level.INFO, str, obj);
        }
        else {
            System.out.println("Logger не должен быть null!");
        }
    }
}
