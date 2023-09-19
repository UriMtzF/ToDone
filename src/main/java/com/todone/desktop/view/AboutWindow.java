package com.todone.desktop.view;

import com.todone.desktop.helpers.NativeOperations;
import com.todone.desktop.helpers.PreferencesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class AboutWindow{
    private ResourceBundle resourceBundle;
    public AboutWindow(Component component) {
        resourceBundle = ResourceBundle.getBundle("content", new Locale(PreferencesManager.getPrefLang()));
        JPanel panel = new JPanel(new GridLayout(10,1));
        panel.add(new JLabel("ToDone",SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel(resourceBundle.getString("description_1"),SwingConstants.CENTER));
        panel.add(new JLabel(resourceBundle.getString("description_2"),SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel("Uri Martínez Florez",SwingConstants.CENTER));
        panel.add(new JSeparator());
        panel.add(new JLabel(resourceBundle.getString("check_source"),SwingConstants.CENTER));
        JButton bGitHub = getjButton();
        panel.add(bGitHub);
        JOptionPane.showMessageDialog(component,panel,resourceBundle.getString("about"),JOptionPane.PLAIN_MESSAGE);
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
