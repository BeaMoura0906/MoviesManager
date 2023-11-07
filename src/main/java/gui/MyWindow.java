package gui;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    private JPanel paneList;

    private JPanel addMoviePane;

    private JPanel addGenrePane;

    private JPanel optionPane;


    public MyWindow(String name){

        super(name);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(800,800));
        // Ouvrir en plein écran
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        JPanel MainPanel = (JPanel) this.getContentPane();
        MainPanel.add(createTabs());


    }

    private JTabbedPane createTabs (){
        //Créer les panneaux
        this.paneList = new MyListPane();
        this.addMoviePane = new MyAddPane();
        this.optionPane = new MyOptionPane();

        //Créer le conteneur des onglets
        JTabbedPane multiPane = new JTabbedPane();

        //Définir la position de conteneur d'onglets
        multiPane.setBounds(40,20,300,300);

        //Associer chaque panneau à l'onglet correspondant
        multiPane.add("Liste des films", paneList);
        multiPane.add("Ajouter un film", addMoviePane);
        multiPane.add("Options", optionPane);


        return multiPane;

    }


}
