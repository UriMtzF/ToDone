package com.todone.desktop;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainWindow extends JFrame {
    MenuBar mbMenuBar;

    public MainWindow() throws HeadlessException {
        ImageIcon icon = new ImageIcon("resources/icon.png");
        this.setTitle("ToDone");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);

        mbMenuBar = new MenuBar();
        this.setJMenuBar(mbMenuBar);

        this.setVisible(true);
    }
}
