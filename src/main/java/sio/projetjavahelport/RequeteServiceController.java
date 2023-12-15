package sio.projetjavahelport;

import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
            ps = cnx.prepareStatement("SELECT sous_matiere FROM `matiere` WHERE designation= ?");
            ps.setString(1, matiere);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String[] mots = rs.getString("sous_matiere").split("#");
                for (String mot : mots) {
                    if (!mot.isEmpty()) {
                        lesSousMatieres.add(mot);
                    }
                }
                lesSousMatieres.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lesSousMatieres;
    }
    public User verifierIdentifiants(String mail, String mdp) {
        User utilisateur = null;
        try {
            cnx = ConnexionBDD.getCnx();
            // System.out.println(cnx);
            ps = this.cnx.prepareStatement("SELECT * FROM user WHERE email =? AND password =? ");
            ps.setString(1, mail);
            ps.setString(2, mdp);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Si une ligne est trouvée, créez un objet User avec les données récupérées
                utilisateur = new User(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("niveau"),
                        rs.getInt("sexe"),
                        rs.getString("telephone")
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return utilisateur;
    }
    public  void saveUserCompetence(int idMatiere, int idUser , String copmpetence)
    {

        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("INSERT INTO competence(id_matiere,id_user,sous_matiere,statut)\n" +
                    "VALUES (?,?,?,1) ");
            ps.setInt(1, idMatiere);
            ps.setInt(2, idUser);
            ps.setString(1, copmpetence);
            rs = ps.executeQuery();
           /* while(rs.next())
            {
                lesMatieres.add(rs.getString(1));
            }*/
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //return lesMatieres;
    }

}
