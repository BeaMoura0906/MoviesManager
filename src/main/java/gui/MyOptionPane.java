package gui;

import back.BddManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyOptionPane extends JPanel {

    private JButton truncate;
    private JButton importSQL;

    public MyOptionPane(){
        this.truncate = new JButton("Restaurer la base de données");
        this.truncate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                truncate();
            }
        });
        this.importSQL = new JButton("Importer la base de données");
        this.importSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importSQL();
            }
        });

        this.add(importSQL);
        this.add(truncate);

    }

    private void truncate(){
        BddManager.truncate();
    }

    private void importSQL(){

    }
}
