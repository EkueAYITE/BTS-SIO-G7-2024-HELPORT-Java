package sio.projetjavahelport;

import sio.projetjavahelport.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequeteServiceController {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public RequeteServiceController()
    {
        cnx = ConnexionBDD.getCnx();
    }
    public ArrayList<String> getDemandesSansSoutient() throws SQLException {
        ArrayList<String> lesMatiere = new ArrayList<>();

        ps = cnx.prepareStatement("SELECT *\n" +
                "FROM demande\n" +
                "WHERE id NOT IN (SELECT id_demande FROM soutien);`");
        rs = ps.executeQuery();
        while(rs.next()) {
            lesMatiere.add(rs.getString(1));
        }
        return lesMatiere;
    }
    public ArrayList<String> GetAllMatieres()
    {
        ArrayList<String> lesMatieres = new ArrayList<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT designation FROM matiere ");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lesMatieres.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lesMatieres;
    }
    public ArrayList<String> GetAllSousMatieres(String matiere)
    {

        ArrayList<String> lesSousMatieres = new ArrayList<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT DISTINCT competence.sous_matiere\n" +
                    "FROM competence\n" +
                    "JOIN matiere ON competence.id_matiere = matiere.id\n" +
                    "WHERE matiere.designation = ?");
            ps.setString(1, matiere);
            rs = ps.executeQuery();
            while(rs.next())
            {
                lesSousMatieres.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lesSousMatieres;
    }
}
