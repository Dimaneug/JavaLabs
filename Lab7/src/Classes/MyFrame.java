package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyFrame extends JFrame {
    private RacePanel mainContainer;
    JLabel winner = new JLabel("", SwingConstants.CENTER);
    public static Boolean isFinished = false;
    public static final int windowWidth = 500;
    public static final int windowHeight = 300;

    MyFrame() {
        mainContainer = new RacePanel();

        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.getContentPane().setSize(windowWidth+16,windowHeight+7);
        this.setSize(windowWidth+16,windowHeight+7);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainContainer, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Старт");
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isFinished = false;
                startRace();
            }
        });

        add(buttonPanel, BorderLayout.PAGE_END);
        add(winner, BorderLayout.PAGE_START);

    }
    public void startRace() {
        winner.setVisible(false);
        Player.speed = (int) (Math.random()*100%15);
        Player racer1 = new Player(0,0);
        Player racer2 = new Player(0,60);
        Player racer3 = new Player(0,120);
        RacePanel.add(racer1, racer2, racer3);
        /*Thread t = new Thread(new MyRunnable(mainContainer, racer1, racer2, racer3, winner));*/
        Thread thread1 = new Thread(new Race(mainContainer, racer1, racer2, racer3, winner, 1));
        Thread thread2 = new Thread(new Race(mainContainer, racer1, racer2, racer3, winner, 2));
        Thread thread3 = new Thread(new Race(mainContainer, racer1, racer2, racer3, winner, 3));
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(Player.speed);

    }

}
