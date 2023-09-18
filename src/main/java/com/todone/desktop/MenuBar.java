package com.todone.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {
    JMenu mFile, mHelp;
    JMenuItem miNew, miSave, miExit, miAbout, miDocs;

    public MenuBar() {
        //TODO: Set multilanguage
        mFile = new JMenu("File");
        mHelp = new JMenu("Help");

        miNew = new JMenuItem("New");
        miSave = new JMenuItem("Save");
        miExit = new JMenuItem("Exit");

        miAbout = new JMenuItem("About");
        miDocs = new JMenuItem("Documentation");

        miNew.addActionListener(this);
        miSave.addActionListener(this);
        miExit.addActionListener(this);

        miAbout.addActionListener(this);
        miDocs.addActionListener(this);

        // TODO: Set mnemonics

        mFile.add(miNew);
        mFile.add(miSave);
        mFile.add(miExit);

        mHelp.add(miDocs);
        mHelp.add(miAbout);

        this.add(mFile);
        this.add(mHelp);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(miExit)) {
            System.exit(0);
        }
    }
}
