package gui;

import back.Genres;
import back.Movies;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MyListPane extends JPanel {
    //On déclare
    private JPanel searchPanel;
    private JPanel contentNorthPanel;
    private JPanel datePanel;
    private JTextField searchInput;
    private JLabel labelNameMovie;
    private JButton searchButton;
    private JComboBox listGenre;
    private JLabel labelGenre;
    private JLabel labelDate;
    private JTextField beginYear;
    private JLabel betweenLabel;
    private JTextField endYear;
    private JTable moviesTable;
    private DefaultTableModel tableModel;

    //Contructeur
    public MyListPane (){

        //On instancie

        this.contentNorthPanel = new JPanel();

        this.searchInput = new JTextField();
        this.labelNameMovie = new JLabel("Nom du film : ");
        this.searchButton = new JButton("Rechercher");
        this.listGenre = new JComboBox();
        for (String genre : Genres.getAllGenres()) {
            this.listGenre.addItem(genre);
        }
        this.labelGenre = new JLabel("Genre : ");

        this.labelDate = new JLabel("Date de sortie :");
        this.beginYear = new JTextField();
        this.betweenLabel = new JLabel("entre");
        this.endYear = new JTextField();


        String[] columns = new String [] {
               "id", "Nom", "Date", "Genre", "#", "#"
        };

        // Indices des colonnes non éditables
        HashSet<Integer> nonEditableColumns = new HashSet<>();
        nonEditableColumns.add(0);
        nonEditableColumns.add(4);  // Colonne 1 (index de colonne 1)
        nonEditableColumns.add(5);  // Colonne 2 (index de colonne 2)
        this.tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Vérifie si la colonne est dans l'ensemble des colonnes non éditables
                return !nonEditableColumns.contains(column);
            }
        };

        ArrayList movies = Movies.allMovies();

        for(int i=0; i<movies.size(); i++){
            HashMap movie = (HashMap) movies.get(i);
            String id = (String) movie.get("id");
            String name = (String) movie.get("nameMovie");
            String year = (String) movie.get("year");
            String genre = (String) movie.get("genre");
            String delete = "Supprimer";
            String update = "Modifier";

            Object[] data = {id, name, year, genre, delete, update};

            tableModel.addRow(data);
        }

        this.moviesTable = new JTable(tableModel);

        TableColumn genreColumn= moviesTable.getColumnModel().getColumn(3);
        JComboBox genresForEdit = new JComboBox();
        for(String genre : Genres.getAllGenres()){
            genresForEdit.addItem(genre);
        }
        genreColumn.setCellEditor(new DefaultCellEditor((genresForEdit)));

        this.moviesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == 1){
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    if(column == 4){
                        String id = (String) tableModel.getValueAt(row, 0);
                        deleteById(id);
                    } else if (column == 5){
                        String id = (String) tableModel.getValueAt(row, 0);
                        String nom = (String) tableModel.getValueAt(row, 1);
                        String date = (String) tableModel.getValueAt(row, 2);
                        String genre = (String) tableModel.getValueAt(row, 3);
                        updateByLine(id, nom, date, genre);

                    }
                }
            }
        });


        //Panel 1
        this.searchPanel = new JPanel();
        this.searchPanel.setLayout(new FlowLayout());
            // Line 1
        this.searchPanel.add(this.labelNameMovie);
        this.searchPanel.add(this.searchInput);

            // Line 2
        this.searchPanel.add(this.labelGenre);
        this.searchPanel.add(this.listGenre);

        //Panel 2
        this.datePanel = new JPanel();
        this.datePanel.setLayout(new FlowLayout());
        this.datePanel.add(this.labelDate);
        this.datePanel.add(this.beginYear);
        this.datePanel.add(this.betweenLabel);
        this.datePanel.add(this.endYear);


        this.contentNorthPanel.setLayout(new FlowLayout());

        this.contentNorthPanel.add(this.searchPanel);
        this.contentNorthPanel.add(this.datePanel);
        this.contentNorthPanel.add(this.searchButton);


        //Size
        this.searchInput.setPreferredSize(new Dimension(100, 30));
        this.listGenre.setPreferredSize(new Dimension(100, 30));
        this.beginYear.setPreferredSize(new Dimension(100, 30));
        this.endYear.setPreferredSize(new Dimension(100, 30));
        this.searchButton.setPreferredSize(new Dimension(100, 30));

        //
        this.setLayout(new BorderLayout());
        this.add(this.contentNorthPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(this.moviesTable), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitResearch();
            }
        });

    }

    private void submitResearch(){
        String search = searchInput.getText();
        String genre = listGenre.getSelectedItem().toString();
        String startYear = beginYear.getText();
        String finalYear = endYear.getText();
        if(search.isEmpty() && genre.equals("aucun") && startYear.isEmpty() && finalYear.isEmpty()) {
            System.out.println("selectAll");
            ArrayList movies = Movies.allMovies();
            this.updateTableMovies(movies);
        }
        else if (!search.isEmpty()) {
            ArrayList movies = Movies.selectByName(search);
            this.updateTableMovies(movies);
        }
        else if (search.isEmpty() && genre.equals("aucun") && !startYear.isEmpty() && !finalYear.isEmpty()){
            int sy = Integer.parseInt(startYear);
            int fy = Integer.parseInt(finalYear);
            ArrayList movies = Movies.selectBetweenDate(sy, fy);
            this.updateTableMovies(movies);
        }
        else if (search.isEmpty() && !genre.equals("aucun") && !startYear.isEmpty() && !finalYear.isEmpty()){
            int sy = Integer.parseInt(startYear);
            int fy = Integer.parseInt(finalYear);
            ArrayList movies = Movies.selectByGenreBetweenDate(genre, sy, fy);
            this.updateTableMovies(movies);
        }
        else if(search.isEmpty() && !genre.equals("aucun")) {
            ArrayList movies = Movies.selectByGenre(genre);
            this.updateTableMovies(movies);
        }
        else {
            System.out.println("pas de recherche");
        }

    }

    private void updateTableMovies (ArrayList movies){
        this.tableModel.setRowCount(0);
        //ArrayList movies = Movies.allMovies();
        for (int i = 0; i < movies.size(); i++){
            HashMap movie = (HashMap)movies.get(i);
            String id = (String) movie.get("id");
            String name = (String) movie.get("nameMovie");
            String year = (String) movie.get("year");
            String genre = (String) movie.get("genre");
            String delete = "Supprimer";
            String update = "Modifier";

            Object[] data = {id, name, year, genre, delete, update};

            this.tableModel.addRow(data);
        }
    }

    private void deleteById (String id){
        Movies.deleteMovie(id);
        System.out.println("Film supprimé");
    }

    private void updateByLine(String id, String nom, String date, String genre){
        Movies.updateMovie(id, nom, date, genre);
        System.out.println("Film modifié");
    }

}
