package sio.projetjavahelport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.projetjavahelport.tools.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequeteServiceController {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    private User user;

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
            ps = cnx.prepareStatement("SELECT d.id, u.niveau, d.date_fin_demande, m.designation, d.sous_matiere, d.id_user, d.id_matiere, d.status\n" +
                    "FROM demande d\n" +
                    "JOIN matiere m ON d.id_matiere = m.id\n" +
                    "JOIN user u ON d.id_user = u.id\n" +
                    "WHERE d.status = 1;");

            rs = ps.executeQuery();
            while(rs.next())
            {
                Demande d = new Demande(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
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
    public ArrayList<Demande> GetMesDemande() {
        ArrayList<Demande> data = new ArrayList<>();
        user = UserHolder.getInstance().getUser();
        try {
            cnx = ConnexionBDD.getCnx();
            String query = "SELECT  d.id, m.designation, d.sous_matiere , d.date_fin_demande\n" +
                    "FROM demande d\n" +
                    "JOIN matiere m ON d.id_matiere = m.id\n" +
                    "JOIN user u ON d.id_user = u.id\n" +
                    "WHERE d.status = 1 AND u.id = ?";
            ps = cnx.prepareStatement(query);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                Demande d = new Demande(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getDate(4));
                data.add(d);
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return data;
    }
    public HashMap<String,Integer> getDatasGraphiqueDemandesParUser()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT  u.nom, COUNT(d.id) AS nombre_demandes\n" +
                    "FROM user u\n" +
                    "LEFT JOIN demande d ON u.id = d.id_user\n" +
                    "GROUP BY u.id, u.nom, u.prenom\n" +
                    "ORDER BY nombre_demandes DESC;");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }
    public HashMap<String,Integer> getDatasGraphiqueTopMatieres()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT m.designation AS matiere, COUNT(d.id) AS nombre_demandes\n" +
                    "FROM matiere m\n" +
                    "LEFT JOIN demande d ON m.id = d.id_matiere\n" +
                    "GROUP BY matiere\n" +
                    "ORDER BY nombre_demandes DESC\n" +
                    "LIMIT 5;");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }
    public HashMap<String,Integer> getDatasGraphiqueDemandesStatuts()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT CASE\n" +
                    "         WHEN status = 1 THEN 'En attente'\n" +
                    "         WHEN status = 2 THEN 'En cours'\n" +
                    "         WHEN status = 3 THEN 'Terminée'\n" +
                    "       END AS statut,\n" +
                    "       COUNT(id) AS nombre_demandes\n" +
                    "FROM demande\n" +
                    "GROUP BY statut;");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }
    public ArrayList<String> CheckMesDemande() {
        ArrayList<String> sousMatieresList = new ArrayList<>();
        try {
            cnx = ConnexionBDD.getCnx();
            String query = "SELECT\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 2), '#', -1) AS sous_matiere_1,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 3), '#', -1) AS sous_matiere_2,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 4), '#', -1) AS sous_matiere_3,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 5), '#', -1) AS sous_matiere_4,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 6), '#', -1) AS sous_matiere_5,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 7), '#', -1) AS sous_matiere_6,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 8), '#', -1) AS sous_matiere_7,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 9), '#', -1) AS sous_matiere_8,\n" +
                    "    SUBSTRING_INDEX(SUBSTRING_INDEX(sous_matiere, '#', 10), '#', -1) AS sous_matiere_9,\n" +
                    "\n" +
                    "\n" +
                    "    status\n" +
                    "    FROM demande;";
            ps = cnx.prepareStatement(query);
            //  ps.setInt(user.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                sousMatieresList.add(rs.getString("sous_matiere_1"));
                sousMatieresList.add(rs.getString("sous_matiere_2"));
                sousMatieresList.add(rs.getString("sous_matiere_3"));
                sousMatieresList.add(rs.getString("sous_matiere_4"));
                sousMatieresList.add(rs.getString("sous_matiere_5"));
                sousMatieresList.add(rs.getString("sous_matiere_6"));
                sousMatieresList.add(rs.getString("sous_matiere_7"));
                sousMatieresList.add(rs.getString("sous_matiere_8"));
                sousMatieresList.add(rs.getString("sous_matiere_9"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sousMatieresList;
    }
    public void createSoutien(int idDemande, int idCompetence, String idSalle, Date dateSoutien, String description) {
        try {
            try (PreparedStatement ps = cnx.prepareStatement("INSERT INTO soutien (id_demande, id_competence, id_salle, date_du_soutien, date_updated, description, status) " +
                    "VALUES (?, ?, ?, ?, CURRENT_DATE, ?, ?)")) {

                ps.setInt(1, idDemande);
                ps.setInt(2, idCompetence);
                ps.setString(3, idSalle);
                ps.setDate(4, dateSoutien);
                ps.setString(5, description);
                ps.setInt(6, 2);

                int rowCount = ps.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Insertion réussie. Lignes affectées : " + rowCount);
                } else {
                    System.out.println("Échec de l'insertion. Aucune ligne affectée.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public int getIdCompetenceCorrespondantALaMatiere(int userId, String matiereNom) {
        int idCompetenceCorrespondantALaMatiere = 0;
        try {
            ps = cnx.prepareStatement("SELECT competence.id \n" +
                    "FROM competence \n" +
                    "INNER JOIN user ON user.id = competence.id_user\n" +
                    "INNER JOIN matiere ON matiere.id = competence.id_matiere\n" +
                    "WHERE user.id = ? \n" +
                    "AND matiere.designation LIKE ?");
            ps.setInt(1, userId);
            ps.setString(2, matiereNom);

            rs = ps.executeQuery();

            if (rs.next()) {
                idCompetenceCorrespondantALaMatiere = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idCompetenceCorrespondantALaMatiere;
    }
    public ArrayList<Soutien> getLesSoutiens(String matiereSelected) {
        ArrayList<Soutien> lesSoutiens = new ArrayList<>();
        user = UserHolder.getInstance().getUser();

        try {
            ps = cnx.prepareStatement("SELECT\n" +
                    "    soutien.*,\n" +
                    "    matiere.designation,\n" +
                    "    (SELECT demande.sous_matiere FROM demande WHERE demande.id = soutien.id_demande) AS competence,\n" +
                    "    (SELECT user.niveau FROM user INNER JOIN demande ON user.id = demande.id_user WHERE demande.id = soutien.id_demande) AS niveauAssiste,\n" +
                    "    (SELECT user.prenom FROM user INNER JOIN demande ON user.id = demande.id_user WHERE demande.id = soutien.id_demande) AS prenomAssiste,\n" +
                    "    (SELECT user.nom FROM user INNER JOIN demande ON user.id = demande.id_user WHERE demande.id = soutien.id_demande) AS nomAssiste\n" +
                    "FROM\n" +
                    "    user\n" +
                    "INNER JOIN competence ON competence.id_user = user.id\n" +
                    "INNER JOIN soutien ON soutien.id_competence = competence.id\n" +
                    "INNER JOIN matiere ON matiere.id = competence.id_matiere\n" +
                    "WHERE\n" +
                    "    user.id = ?\n" +
                    "AND matiere.designation LIKE ? ");

            ps.setInt(1, user.getId());
            ps.setString(2, "%" + matiereSelected + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                String soutienId = rs.getString("id");
                String designation = rs.getString("designation");
                String niveauAssiste = rs.getString("niveauAssiste");
                String nomAssiste = rs.getString("nomAssiste");
                String prenomAssiste = rs.getString("prenomAssiste");
                String competence = rs.getString("competence");
                String idCompetence = rs.getString("id_competence");
                String idDemande = rs.getString("id_demande");
                String idSalle = rs.getString("id_salle");
                String description = rs.getString("description");
                int statue = rs.getInt("status");
                java.util.Date dateUpdate = rs.getDate("date_updated");
                java.util.Date dateDuSoutien = rs.getDate("date_du_soutien");

                String[] competencesArray = competence.replaceAll("[^\\p{L}#]", "").split("#");
                List<String> sousMatieresList = new ArrayList<>();

                for (String competenceD : competencesArray) {
                    if (!competenceD.isEmpty()) {
                        sousMatieresList.add(competenceD);
                    }
                }

                Soutien unSoutien = new Soutien(soutienId, designation, niveauAssiste, nomAssiste, prenomAssiste, sousMatieresList, idDemande,idCompetence, idSalle, description, statue, dateUpdate, dateDuSoutien);

                lesSoutiens.add(unSoutien);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lesSoutiens;
    }
    public void DeleteDemande(int demandeId) {
        try {
            ps = cnx.prepareStatement("DELETE FROM demande WHERE id = ?");
            ps.setInt(1, demandeId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Demande supprimée avec succès.");
            } else {
                System.out.println("Aucune demande correspondante trouvée pour la suppression.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDemande(int demandeId) {

    }
}