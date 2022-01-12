package classes;

import java.awt.*;
import java.lang.reflect.Field;
import javax.swing.*;

public class DrawComponent extends JPanel {
    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        long max = Main.params[0][0];
        for(int i=0; i<2; i++)
            for(int j=0; j<5; j++)
                if(Main.params[i][j]>max)
                    max=Main.params[i][j];
        long dy = max/9;
        for (int i=2; i<11; i++) {
            drp.drawLine(75, 50+50*i, 550, 50+50*i);
            long vs = max - (i-2)*dy;
            drp.drawString(vs+"", 10, 50+50*i);
        }
        drp.drawLine(75, 50+50*11, 550, 50+50*11);
        drp.drawString(0+"", 10, 50+50*11);
        drp.drawString("ArrayList", 100, 40);
        drp.drawString("HashMap", 100, 60);

        drp.drawString("10", 70, 640);
        drp.drawString("100", 170, 640);
        drp.drawString("1000", 270, 640);
        drp.drawString("10000", 370, 640);
        drp.drawString("100000", 470, 640);

        drp.setColor(Main.col[0]);
        drp.fillRect(80, 30, 10, 10);
        drp.setColor(Main.col[1]);
        drp.fillRect(80, 50, 10, 10);

        for (int i=0; i<5; i++) {
            for (int j=0;j<2;j++) {
                drp.setColor(Main.col[j]);
                int realY = (int) (600-50*Main.params[j][i]/dy);
                drp.fillRect(75+20*j+100*i, realY, 20,(int) (Main.params[j][i]*50/dy));
            }
        }
    }
}