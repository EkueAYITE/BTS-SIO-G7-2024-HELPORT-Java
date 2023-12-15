package sio.projetjavahelport.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnexionBDD {

    private static Connection cnx;

    public ConnexionBDD() throws ClassNotFoundException, SQLException {
        String pilote = "com.mysql.jdbc.Driver";
        //String pilote = "com.mysql.cj.jdbc.Driver";
        // chargement du pilote
        Class.forName(pilote);
        // L'objet connexion à la BDD avec le nom de la base, le user et le password
        cnx = DriverManager.getConnection("jdbc:mysql://localhost/BddHelpOrtJava?useSSL=true&serverTimezone="
                + TimeZone.getDefault().getID(), "root", "");
        System.out.println(cnx);
    }
    public static Connection getCnx() {
        return cnx;

    }
}
