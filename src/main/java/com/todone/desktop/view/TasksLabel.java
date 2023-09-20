package com.todone.desktop.view;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class TasksLabel extends JLabel {
    private JCheckBox cbDone;
    private JTextField tfTaskTitle;

    public TasksLabel(String taskTitle, String dueDate) {
        this.setLayout(new GridLayout(1,3));
        this.cbDone = new JCheckBox();
        this.tfTaskTitle = new JTextField();
    }
}
