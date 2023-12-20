package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {

    ConnexionBDD maCnx;
    RequeteServiceController requeteServ;
    @javafx.fxml.FXML
    private Button btnCompetencesAccueil;
    @javafx.fxml.FXML
    private Button btnStatistiquesAccueil;
    @javafx.fxml.FXML
    private Label lblContactsAccueil;
    @javafx.fxml.FXML
    private Label lblMentionsLegagesAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterCompetence;
    @javafx.fxml.FXML
    private Button btnSupprimerCompetence;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnSelectionnerDemande;
    @javafx.fxml.FXML
    private Button btnDeconnexionAccueil;
    @javafx.fxml.FXML
    private Button btnParametresAccueil;
    @javafx.fxml.FXML
    private AnchorPane apnStatistiques;
    @javafx.fxml.FXML
    private AnchorPane apnCompetences;
    @javafx.fxml.FXML
    private AnchorPane apnDemandes;
    private Stage fenetre = null;
    @javafx.fxml.FXML
    private Button btnModifierDemande1;
    @javafx.fxml.FXML
    private ComboBox cboStatistique;
    @javafx.fxml.FXML
    private Button btnSelectionnerDemande1;
    @javafx.fxml.FXML
    private Button btnSoutiensAccueil;
    @javafx.fxml.FXML
    private TableView TblVValiderSoutiens;
    @javafx.fxml.FXML
    private ComboBox cboMatiereCompetence;
    @javafx.fxml.FXML
    private Label lblNomEleve;
    @javafx.fxml.FXML
    private Label lblRoleEleve;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cboStatistique.getItems().addAll("Nombre de soutiens réalisés","Nombre de demandes restées sans soutien","Nombre de soutiens réalisés par niveau, par matière","Demandes par niveau, par matière","Etudiants qui ont réalisé le plus de soutiens","Sous matières les plus sollicitées");

       try{
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

           HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
           for(int matiere : matieres.keySet()){
               String nomMatiere = matieres.get(matiere);
               cboMatiereCompetence.getItems().add(nomMatiere);
           }

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cboMatiereCompetence.getSelectionModel().selectFirst();

       user = UserHolder.getInstance().getUser();
       lblNomEleve.setText(user.getNom());
       lblRoleEleve.setText(user.getRole());
    }
    @Deprecated
    private void receiveData(MouseEvent event) {

    }



    @javafx.fxml.FXML
    public void btnCompetencesAccueilClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(true);
        apnStatistiques.setVisible(false);
    }

    @javafx.fxml.FXML
    public void btnStatistiquesAccueilClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(false);
        apnStatistiques.setVisible(true);
    }

    @javafx.fxml.FXML
    public void lblContactsAccueilClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Contacts");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void lblMentionsLegagesAccueilClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mentions-legales-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Mentions légales");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }



    @javafx.fxml.FXML
    public void btnAjouterCompetenceClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Ajouter une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("supprimerCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Supprimer une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @Deprecated
    public void btnModifierCompetenceClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifierCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Modifier une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("creerDemande-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Créer une demande");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnSelectionnerDemandeClicked(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void btnDeconnexionAccueilClicked(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connexion-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Connexion");
        stage.setScene(scene);


        Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
        Stage stageActuel = (Stage) sceneActuelle.getWindow();
        stageActuel.close();
        stage.show();
    }
    @javafx.fxml.FXML
    public void btnParametresAccueilClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("parametres-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Paramètres");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnModifierDemandeClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifierDemande-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Modifier une demande");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            fenetre.show();
        }
    }


    @javafx.fxml.FXML
    public void btnSoutiensClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(true);
        apnCompetences.setVisible(false);
        apnStatistiques.setVisible(false);
    }

    @Deprecated
    public void cboMatièreCompetenceCliked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cboMatiereCompetenceCliked(ActionEvent actionEvent) {
    }


}
