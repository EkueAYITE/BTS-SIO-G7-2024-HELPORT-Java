package sio.projetjavahelport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.Demande;
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
    public HashMap<Integer,String> GetAllMatieres()
    {
        HashMap<Integer,String> lesMatieres = new HashMap<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT id ,designation FROM matiere ");
            rs = ps.executeQuery();
            while(rs.next())
            {
                lesMatieres.put(rs.getInt(1),rs.getString(2));

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
             //   lesSousMatieres.add(rs.getString(1));
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
                        rs.getInt("id"),
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
            ps.setString(3, copmpetence);
            ps.executeUpdate();
           /* while(rs.next())
            {
                lesMatieres.add(rs.getString(1));
            }*/
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //return lesMatieres;
    }
    public ArrayList<String> GetSousMatiereByMatiere(int idUser, int idMatiere)
    {
        ArrayList<String> lesSousMatieres = new ArrayList<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT sous_matiere FROM `competence` WHERE id_user=? and id_matiere=? ");
            ps.setInt(1, idUser);
            ps.setInt(2,idMatiere);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String[] mots = rs.getString("sous_matiere").split("#");
                for (String mot : mots) {
                    if (!mot.isEmpty()) {
                        lesSousMatieres.add(mot);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lesSousMatieres;
    }
    public ArrayList<Demande> GetDemande()
    {
        ArrayList<Demande> data = new ArrayList<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT u.niveau, d.date_fin_demande, m.designation, d.sous_matiere\n" +
                    "FROM demande d\n" +
                    "JOIN matiere m ON d.id_matiere = m.id\n" +
                    "JOIN user u ON d.id_user = u.id\n" +
                    "WHERE d.status = 1;");

            rs = ps.executeQuery();
            while(rs.next())
            {
                Demande d = new Demande(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4));
                data.add(d);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return data;

    }

    public void DeletCompetence(String sousMatiere, int idMatiere, int idUser){
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("UPDATE competence\n" +
                    "    set sous_matiere = ?\n" +
                    "    WHERE id_matiere = ?\n" +
                    "    AND id_user = ?" );
            ps.setString(1, sousMatiere);
            ps.setInt(2, idMatiere);
            ps.setInt(3, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ajouterDemande(Demande demande) throws SQLException {
        String query = "INSERT INTO `demande` (`date_updated`, `date_fin_demande`, `sous_matiere`, `id_user`, `id_matiere`, `status`) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setDate(1, demande.getDate_updated());
            ps.setDate(2, demande.getDateFinDemande());
            ps.setString(3, demande.getSousMatiere());
            ps.setInt(4, demande.getId_user());
            ps.setInt(5, demande.getId_matiere());
            ps.setInt(6, demande.getStatus());

            ps.executeUpdate();
        }
    }









}
