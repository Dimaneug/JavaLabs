package Classes;

import javax.swing.*;
import java.awt.*;

public class RacePanel extends JPanel {
    private static Color color = Color.GRAY;
    private static Player[] players = new Player[3];

    public Color getColor(){
        return color;
    }
    public static void add(Player a, Player b, Player c)
    {
        players[0] = a;
        players[1] = b;
        players[2] = c;
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D my2DGraphics = (Graphics2D) graphics;
        for (Player player : players)
        {
            if (player != null)
            {
                my2DGraphics.setPaint(player.getColor());
                my2DGraphics.fill(player.getShape());
                if (player.getX() >= MyFrame.windowWidth)
                {
                    my2DGraphics.setPaint(Color.GREEN);
                    my2DGraphics.fill(player.getShape());
                }
            }
        }
    }
}
