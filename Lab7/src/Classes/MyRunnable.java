package Classes;

import javax.swing.*;
import java.awt.*;

public class MyRunnable implements Runnable{

    private final Player player1;
    private final Component component;
    JLabel winner;

    /*public MyRunnable(Component component, Player player1, Player player2, Player player3, JLabel win)
    {
        this.component = component;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        winner = win;
    }*/
    public MyRunnable(Component component, Player player1, JLabel win)
    {
        this.component = component;
        this.player1 = player1;
        winner = win;
    }


    public void run()
    {
        try
        {
            while( (player1.getX() < component.getBounds().getWidth())/* &&
                    (player2.getX() < component.getBounds().getWidth()) &&
                    (player3.getX() < component.getBounds().getWidth()) */)
            {
                player1.move(component.getBounds());
                System.out.println("Now "+Thread.currentThread());

                /*player2.move(component.getBounds(),5);
                System.out.println("Now "+Thread.currentThread());
                Thread.yield();
                player3.move(component.getBounds(),5);
                System.out.println("Now "+Thread.currentThread());
                Thread.yield();*/
                component.repaint();
                Thread.sleep(10);
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
		/*if (player2.getX() >= component.getBounds().getWidth())
		{
			winner.setVisible(true);
			winner.setText("Выиграл 2");
		}
		if (player3.getX() >= component.getBounds().getWidth())
		{
			winner.setVisible(true);
			winner.setText("Выиграл 3");
		}*/
    }
}
