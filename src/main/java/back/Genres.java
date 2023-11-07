package back;

import java.sql.*;
import java.util.ArrayList;

public class Genres {

    public static ArrayList<String> getAllGenres(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> genres = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            stmt = conn.createStatement();
            String selectQuery = "SELECT nom FROM genres";
            rs = stmt.executeQuery(selectQuery);

            //System.out.println("---------------------------------------------------");

            while(rs.next()){
                String name = rs.getString("nom");
                genres.add(name);
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return genres;

    }

    public static int getIdOfGenre (String genre){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int idOfGenre = 0;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String query = "SELECT id FROM Genres WHERE nom = '" + genre + "' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                //System.out.println(rs.getString("id"));
                idOfGenre = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idOfGenre;
    }

    public static void addGenre (String genre){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String sqlQuery = "INSERT INTO genres (`nom`) VALUES (?)";

            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, genre);


            stmt.executeUpdate();

            // Fermeture du Statement
            if(stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }
    }
}
