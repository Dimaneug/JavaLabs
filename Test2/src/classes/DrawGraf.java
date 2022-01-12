package classes;

import javax.swing.*;
import java.awt.*;

public class DrawGraf extends JFrame{
    public DrawGraf(String name, boolean hora) {
        super(name);
        if(hora)//true = add
            Main.params = ForestArray.Added;
        else
            Main.params = ForestArray.Removed;
        JPanel jp = new JPanel(new BorderLayout());
        setContentPane(jp);
        jp.add(new DrawComponent(), BorderLayout.CENTER);

        jp.setBackground(Color.white);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}