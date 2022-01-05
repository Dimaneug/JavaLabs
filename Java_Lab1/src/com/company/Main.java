package com.company;

import Classes.Ex1;
import Classes.Ex1Controller;
import Classes.Ex1View;

import java.util.Arrays;
import java.util.Scanner;

/*
@author Дмитрий
@version 0.1
*/

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Ex1 model = retrieveEx1FromDatabase();
        Ex1View view = new Ex1View();
        Ex1Controller controller = new Ex1Controller(model, view);

        String[] strs = new String[10];
        for (int i = 0; i < 10; i++) {
            strs[i] = in.nextLine();
        }
        Arrays.sort(strs);
        controller.setEx1Strs(strs);
        controller.updateView();

    }

    private static Ex1 retrieveEx1FromDatabase() {
        Ex1 ex1 = new Ex1();
        String[] strs = new String[10];
        for (int i = 0; i < strs.length; i++)
            strs[i] = "";
        ex1.setStrs(strs);
        return ex1;
    }
}
