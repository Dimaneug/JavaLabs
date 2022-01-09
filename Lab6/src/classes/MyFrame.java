package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
    public MyFrame(String title)
    {
        super(title);
        this.setBackground(Color.BLUE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(600,400);
        //this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Фамилия");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(label1, c);

        JTextField surname = new JTextField(15);
        surname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isAlphabetic(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5, 0,0,0);
        this.add(surname,c);

        JLabel label2 = new JLabel("Имя");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(label2,c);

        JTextField name = new JTextField(10);
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isAlphabetic(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 0,0,0);
        this.add(name,c);

        JLabel label3 = new JLabel("Телефон");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        this.add(label3,c);

        JTextField phone = new JTextField(11);
        phone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5, 0,0,0);
        this.add(phone,c);

        Box importance = Box.createVerticalBox();
        ButtonGroup bg = new ButtonGroup();
        JRadioButton rButton1 = new JRadioButton("Очень важно", false);
        rButton1.setOpaque(false);
        rButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rButton1.isSelected()) {
                    MyFrame.this.getContentPane().setBackground(new Color(250, 128, 114));
                }
            }
        });
        JRadioButton rButton2 = new JRadioButton("Важно", false);
        rButton2.setOpaque(false);
        rButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rButton2.isSelected()) {
                    MyFrame.this.getContentPane().setBackground(new Color(240, 230, 140));
                }
            }
        });
        JRadioButton rButton3 = new JRadioButton("Неважно", false);
        rButton3.setOpaque(false);
        rButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rButton3.isSelected()) {
                    MyFrame.this.getContentPane().setBackground(new Color(154, 205, 50));
                }
            }
        });

        bg.add(rButton1);
        bg.add(rButton2);
        bg.add(rButton3);
        importance.add(rButton1);
        importance.add(rButton2);
        importance.add(rButton3);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 0,0,0);
        this.add(importance,c);

        JButton button1 = new JButton("Отправить форму");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.this.dispose();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        this.add(button1, c);


    }

    public static void main(String [] args)
    {
        MyFrame mylayout = new MyFrame("Форма заказа");
        mylayout.setVisible(true);
    }


}
