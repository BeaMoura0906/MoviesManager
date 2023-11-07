package back;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;

public class BddManager {

    public static void test(){
        Connection con = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost/firstapp", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Nom du fichier ex : C:sql/bdd.sql"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sr.runScript(reader);
    }

    public static void truncate() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appmovies", "root", "");
            stmt = conn.createStatement();
            String selectQuery = "TRUNCATE TABLE 'movies', 'genres'";
            rs = stmt.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
