package Classes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {
    private int x = 0;
    private int y = 0;
    private final int xSize = 70;
    private final int ySize = 50;
    public static int speed;
    private Color color = Color.BLUE;

    public Player(int a, int b)
    {
        x = a;
        y = b;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color c) {
        color = c;
    }
    public void move(Rectangle2D bounds)
    {
        x += speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }

    public Rectangle2D getShape()
    {
        return new Rectangle2D.Double(x, y, xSize, ySize);
    }

    public int getX()
    {
        return x + xSize;
    }
}
