package back;

import java.lang.reflect.GenericDeclaration;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Movies {

    public static ArrayList<HashMap> allMovies(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            stmt = conn.createStatement();
            String selectQuery = "SELECT m.id,m.nom,releaseDate,g.nom AS genre FROM movies m JOIN genres g ON m.genre_id = g.id";
            rs = stmt.executeQuery(selectQuery);

            //System.out.println("---------------------------------------------------");
            while(rs.next()){
                String idMovie = rs.getString("id");
                String nameMovie = rs.getString("nom");
                String year = rs.getString("releaseDate");
                String genre = rs.getString("genre");
                HashMap movie = new HashMap();
                movie.put("id", idMovie);
                movie.put("nameMovie", nameMovie);
                movie.put("year", year);
                movie.put("genre", genre);
                movies.add(movie);

            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return movies;

    }

    public static ArrayList<HashMap> selectByName(String name){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String query = "SELECT m.id,m.nom,releaseDate,g.nom AS genre FROM movies m JOIN genres g ON m.genre_id = g.id WHERE m.nom = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            //System.out.println("---------------------------------------------------");
            while(rs.next()){
                String idMovie = rs.getString("id");
                String nameMovie = rs.getString("nom");
                String year = rs.getString("releaseDate");
                String genre = rs.getString("genre");
                HashMap movie = new HashMap();
                movie.put("id", idMovie);
                movie.put("nameMovie", nameMovie);
                movie.put("year", year);
                movie.put("genre", genre);
                movies.add(movie);

            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return movies;

    }

    public static ArrayList<HashMap> selectBetweenDate(Integer beginDate, Integer endDate){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String query = "SELECT m.id,m.nom,releaseDate,g.nom AS genre FROM movies m JOIN genres g ON m.genre_id = g.id WHERE releaseDate BETWEEN ? AND ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, beginDate);
            stmt.setInt(2, endDate);

            rs = stmt.executeQuery();

            //System.out.println("---------------------------------------------------");
            while(rs.next()){
                String idMovie = rs.getString("id");
                String nameMovie = rs.getString("nom");
                String year = rs.getString("releaseDate");
                String genre = rs.getString("genre");
                HashMap movie = new HashMap();
                movie.put("id", idMovie);
                movie.put("nameMovie", nameMovie);
                movie.put("year", year);
                movie.put("genre", genre);
                movies.add(movie);

            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return movies;

    }

    public static ArrayList<HashMap> selectByGenre(String nameGenre){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String query = "SELECT m.id,m.nom,releaseDate,g.nom AS genre FROM movies m JOIN genres g ON m.genre_id = g.id WHERE g.nom = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameGenre);

            rs = stmt.executeQuery();

            //System.out.println("---------------------------------------------------");
            while(rs.next()){
                String idMovie = rs.getString("id");
                String nameMovie = rs.getString("nom");
                String year = rs.getString("releaseDate");
                String genre = rs.getString("genre");
                HashMap movie = new HashMap();
                movie.put("id", idMovie);
                movie.put("nameMovie", nameMovie);
                movie.put("year", year);
                movie.put("genre", genre);
                movies.add(movie);

            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return movies;

    }

    public static ArrayList<HashMap> selectByGenreBetweenDate(String nameGenre, Integer beginDate, Integer endDate){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap> movies = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String query = "SELECT m.id,m.nom,releaseDate,g.nom AS genre FROM movies m JOIN genres g ON m.genre_id = g.id WHERE g.nom = ? releaseDate BETWEEN ? AND ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameGenre);
            stmt.setInt(2, beginDate);
            stmt.setInt(3, endDate);

            rs = stmt.executeQuery();

            //System.out.println("---------------------------------------------------");
            while(rs.next()){
                String idMovie = rs.getString("id");
                String nameMovie = rs.getString("nom");
                String year = rs.getString("releaseDate");
                String genre = rs.getString("genre");
                HashMap movie = new HashMap();
                movie.put("id", idMovie);
                movie.put("nameMovie", nameMovie);
                movie.put("year", year);
                movie.put("genre", genre);
                movies.add(movie);

            }

        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException : " +ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        }

        return movies;

    }

    public static void addMovie(String nom, String releaseDate, Integer genre_id){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String sqlQuery = "INSERT INTO movies (`nom`, `releaseDate`, `genre_id`) VALUES (?,?,?)";

            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, nom);
            stmt.setString(2, releaseDate);
            stmt.setInt(3, genre_id);

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

    public static void deleteMovie(String _id){
        int id = Integer.parseInt(_id);
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String sqlQuery = "DELETE FROM movies WHERE id = ?";

            stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, id);

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

    public static void updateMovie(String _id, String nom, String date, String _genre){
        int id = Integer.parseInt(_id);
        Connection conn = null;
        PreparedStatement stmt = null;

        int genre = Genres.getIdOfGenre(_genre);
        String newDate = date.substring(0,4);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            String sqlQuery = "UPDATE movies SET nom = ?, releaseDate = ?, genre_id = ? WHERE id = ?";

            stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, nom);
            stmt.setString(2, newDate);
            stmt.setInt(3, genre);
            stmt.setInt(4, id);


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
