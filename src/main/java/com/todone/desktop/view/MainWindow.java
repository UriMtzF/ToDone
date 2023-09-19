package com.todone.desktop.view;

import com.todone.desktop.helpers.PreferencesManager;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindow extends JFrame {
    private MenuBar mbMenuBar;
    private ToolBar pToolBar;

    public MainWindow() throws HeadlessException {
        ImageIcon icon = new ImageIcon("resources/icon.png");
        this.setTitle("ToDone");
        this.setLayout(new BorderLayout(0,10));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);
        this.setLocationRelativeTo(null);

        mbMenuBar = new MenuBar();
        pToolBar = new ToolBar();

        this.setJMenuBar(mbMenuBar);
        this.add(pToolBar,BorderLayout.NORTH);
        this.add(new JSeparator());

        this.setVisible(true);
    }
}
