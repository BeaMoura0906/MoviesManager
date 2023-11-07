package gui;

import back.Genres;
import back.Movies;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyAddPane extends JPanel implements SimpleUpdate {


    private JTextField nom;
    private JLabel labelNom;
    private JComboBox listGenre;
    private JLabel labelGenre;

    private JTextField releaseDate;
    private JLabel labelDate;

    private JButton addMovie;

    private JTextField nomGenre;
    private JLabel labelNomGenre;
    private JButton addGenre;
    public MyAddPane(){

        this.labelNom = new JLabel("Nom du film");
        this.nom = new JTextField();
        this.nom.setPreferredSize(new Dimension(300,30));
        this.labelDate = new JLabel("Année de sortie");
        this.releaseDate = new JTextField();
        this.releaseDate.setPreferredSize(new Dimension(150,30));
        this.labelGenre = new JLabel("Genre du film à ajouter");
        this.listGenre = new JComboBox();
        this.listGenre.setPreferredSize(new Dimension(150,30));
        this.addMovie = new JButton("Ajouter un film ");
        for (String genre : Genres.getAllGenres()) {
            this.listGenre.addItem(genre);
        }
        this.nomGenre = new JTextField();
        this.nomGenre.setPreferredSize(new Dimension(300,30));
        this.labelNomGenre = new JLabel("Nom du genre");
        this.addGenre = new JButton("Ajouter le genre");

        this.addGenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOneGenre();
            }
        });

        this.setLayout(new FlowLayout());
        this.add(this.labelNom);
        this.add(this.nom);
        this.add(this.labelDate);
        this.add(this.releaseDate);
        this.add(this.labelGenre);
        this.add(this.listGenre);
        this.add(this.addMovie);
        this.add(this.labelNomGenre);
        this.add(this.nomGenre);
        this.add(this.addGenre);

        this.addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOneMovie();
            }
        });

    }

    private  void addOneGenre(){
        String genre =  this.nomGenre.getText();
        Genres.addGenre(genre);
    }
    private void addOneMovie(){
        String nom = this.nom.getText();
        String year = this.releaseDate.getText();
        String genre = this.listGenre.getSelectedItem().toString();
        int idOfGenre = getIdOfGenre(genre);
        Movies.addMovie(nom,year,idOfGenre);

    }

    private int getIdOfGenre(String g){

        return  Genres.getIdOfGenre(g);

    }

    @Override
    public DefaultTableModel update(ArrayList movies) {
        return null;
    }
}