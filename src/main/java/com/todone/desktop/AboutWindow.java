package com.todone.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutWindow{
    public AboutWindow(Component component) {
        JPanel panel = new JPanel(new GridLayout(10,1));
        panel.add(new JLabel("ToDone",SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel("A Java app inspired by the todo.txt format \n",SwingConstants.CENTER));
        panel.add(new JLabel("featuring its own format and parser giving more flexible and advanced features.",SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel("Uri Martínez Florez",SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel("Check the source code on",SwingConstants.CENTER));
        JButton bGitHub = getjButton();
        panel.add(bGitHub);
        JOptionPane.showMessageDialog(component,panel,"About",JOptionPane.PLAIN_MESSAGE);
    }

    private static JButton getjButton() {
        JButton bGitHub = new JButton();
        bGitHub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NativeOperations.openOnDefaultBrowser("https://github.com/UriMtzF/ToDone");
            }
        });
        ImageIcon githubBigIcon = new ImageIcon("resources/github-mark.png");
        Image originalImage = githubBigIcon.getImage();
        Image newImage = originalImage.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon githubIcon = new ImageIcon(newImage);
        bGitHub.setIcon(githubIcon);
        return bGitHub;
    }
}
