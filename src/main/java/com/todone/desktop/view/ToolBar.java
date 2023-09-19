package com.todone.desktop.view;

import com.todone.desktop.helpers.PreferencesManager;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ToolBar extends JPanel {
    private ResourceBundle resourceBundle;
    private JButton bCreate;
    private JComboBox<String> cbFilter;
    public ToolBar() {
        super(new BorderLayout());
        resourceBundle = ResourceBundle.getBundle("content", new Locale(PreferencesManager.getPrefLang()));
        bCreate = new JButton(resourceBundle.getString("new_task"));

        String[] filters = {resourceBundle.getString("default"),resourceBundle.getString("priority_a-z"),resourceBundle.getString("priority_z-a"),
                resourceBundle.getString("due_asc"),resourceBundle.getString("due_desc"),resourceBundle.getString("creation_asc"),resourceBundle.getString("creation_desc")};
        cbFilter = new JComboBox<>(filters);
        cbFilter.setSelectedIndex(0);

        this.add(bCreate,BorderLayout.WEST);
        this.add(cbFilter,BorderLayout.EAST);
    }
}
