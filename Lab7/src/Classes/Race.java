package Classes;

import javax.swing.*;
import java.awt.*;

public class Race extends Thread{
    private final Player player1, player2, player3;
    private final Component component;
    JLabel winner;
    int num;

    public Race(Component component, Player player1, Player player2, Player player3, JLabel win, int num)
    {
        this.component = component;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        winner = win;
        this.num = num;
    }

    public void run()
    {
        try
        {
            while( (player1.getX() < component.getBounds().getWidth()) &&
                    (player2.getX() < component.getBounds().getWidth()) &&
                    (player3.getX() < component.getBounds().getWidth()) )
            {
                if (num == 1) {
                    player1.move(component.getBounds());
                    System.out.println("Now " + Thread.currentThread());
                    Thread.sleep(10);
                }
                if (num == 2) {
                    player2.move(component.getBounds());
                    System.out.println("Now " + Thread.currentThread());
                    Thread.sleep(10);
                }
                if (num == 3) {
                    player3.move(component.getBounds());
                    System.out.println("Now " + Thread.currentThread());
                    Thread.sleep(10);
                }
                component.repaint();
                Thread.sleep(200);
            }

        }
        catch(InterruptedException e)
        {
            return;
        }
        if (player1.getX() >= component.getBounds().getWidth())
        {
            winner.setVisible(true);
            winner.setText("Выиграл 1");
        }
        if (player2.getX() >= component.getBounds().getWidth())
        {
            winner.setVisible(true);
            winner.setText("Выиграл 2");
        }
        if (player3.getX() >= component.getBounds().getWidth())
        {
            winner.setVisible(true);
            winner.setText("Выиграл 3");
        }
    }
}
