package com.todone.desktop;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    private JButton bCreate;
    private JComboBox<String> cbFilter;
    public ToolBar() {
        super(new BorderLayout());
        bCreate = new JButton("New Task");

        String[] filters = {"Default","Priority A-Z","Priority Z-A",
                "Due (Asc)","Due (Desc)","Creation (Asc)","Creation (Desc)"};
        cbFilter = new JComboBox<>(filters);
        cbFilter.setSelectedIndex(0);

        this.add(bCreate,BorderLayout.WEST);
        this.add(cbFilter,BorderLayout.EAST);
    }
}
