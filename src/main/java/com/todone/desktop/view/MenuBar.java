package com.todone.desktop.view;

import com.todone.desktop.helpers.NativeOperations;
import com.todone.desktop.helpers.PreferencesManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuBar extends JMenuBar implements ActionListener {
    private ResourceBundle resourceBundle;
    JMenu mFile, mHelp, mLanguage;
    JMenuItem miNew, miSave, miExit, miAbout, miDocs;
    JCheckBoxMenuItem cbmiEsItem, cbmiEnItem;

    public MenuBar() {
        resourceBundle = ResourceBundle.getBundle("content", new Locale(PreferencesManager.getPrefLang()));
        mFile = new JMenu(resourceBundle.getString("file"));
        mHelp = new JMenu(resourceBundle.getString("help"));
        mLanguage = new JMenu(resourceBundle.getString("language"));

        miNew = new JMenuItem(resourceBundle.getString("new"));
        miSave = new JMenuItem(resourceBundle.getString("save"));
        miExit = new JMenuItem(resourceBundle.getString("exit"));

        miAbout = new JMenuItem(resourceBundle.getString("about"));
        miDocs = new JMenuItem(resourceBundle.getString("documentation"));

        cbmiEsItem = new JCheckBoxMenuItem("Español");
        cbmiEnItem = new JCheckBoxMenuItem("English");
        checkDefaultSelectedLanguage();

        miNew.addActionListener(this);
        miSave.addActionListener(this);
        miExit.addActionListener(this);

        miAbout.addActionListener(this);
        miDocs.addActionListener(this);

        cbmiEnItem.addActionListener(this);
        cbmiEsItem.addActionListener(this);

        // TODO: Set mnemonics

        mFile.add(miNew);
        mFile.add(miSave);
        mFile.add(mLanguage);
        mFile.add(miExit);

        mHelp.add(miDocs);
        mHelp.add(miAbout);

        mLanguage.add(cbmiEnItem);
        mLanguage.add(cbmiEsItem);

        this.add(mFile);
        this.add(mHelp);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(miDocs)){
            NativeOperations.openOnDefaultBrowser("https://github.com/UriMtzF/ToDone/wiki");
        }
        if (actionEvent.getSource().equals(miAbout)){
            new AboutWindow(this);
        }
        if (actionEvent.getSource().equals(miExit)) {
            System.exit(0);
        }
        if (actionEvent.getSource().equals(cbmiEnItem)){
            PreferencesManager.setPrefLang("en");
            restartAlert();
        }
        if (actionEvent.getSource().equals(cbmiEsItem)){
            PreferencesManager.setPrefLang("es");
            restartAlert();
        }
    }
    private void checkDefaultSelectedLanguage(){
        switch (PreferencesManager.getPrefLang()){
            case "en": cbmiEnItem.setSelected(true); break;
            case "es": cbmiEsItem.setSelected(true); break;
        }
    }
    private void restartAlert(){
        JOptionPane.showMessageDialog(this,resourceBundle.getString("restart_apply"),resourceBundle.getString("restart"), JOptionPane.INFORMATION_MESSAGE);
    }
}
