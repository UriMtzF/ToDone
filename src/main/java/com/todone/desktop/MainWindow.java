package com.todone.desktop;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private MenuBar mbMenuBar;
    private ToolBar pToolBar;

    public MainWindow() throws HeadlessException {
        //TODO: Set multilanguage
        ImageIcon icon = new ImageIcon("resources/icon.png");
        this.setTitle("ToDone");
        this.setLayout(new BorderLayout(0,10));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);

        mbMenuBar = new MenuBar();
        pToolBar = new ToolBar();

        this.setJMenuBar(mbMenuBar);
        this.add(pToolBar,BorderLayout.NORTH);
        this.add(new JSeparator());

        this.setVisible(true);
    }
}
