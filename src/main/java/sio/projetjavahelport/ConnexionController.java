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
import javafx.stage.Stage;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {


    @FXML
    private TextField txtIdentifiant;
    @FXML
    private Button btnConnexion;
    @FXML
    private Label lblMotDePasseOublie;
    @FXML
    private Label lblMentionsLegales;
    @FXML
    private Label lblContact;
    private Stage fenetre = null;
    @FXML
    private Label lblInscription;
    @FXML
    private PasswordField txtMotDePasse;
    private ConnexionBDD maCnx;
    RequeteServiceController requeteServ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnConnexionClicked(ActionEvent actionEvent) {
        Stage stageActuel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.requeteServ = new RequeteServiceController();
        String email = txtIdentifiant.getText();
        String password = txtMotDePasse.getText();
        if (email.isEmpty() || password.isEmpty()) {
            // Affiche un message d'erreur ou effectuez une action en cas d'échec
            Alert alert = new Alert(Alert.AlertType.ERROR, "Identifiant ou mot de passe vide");
            alert.showAndWait(); // Affiche l'alerte de manière synchrone
        }

            User userInfo = this.requeteServ.verifierIdentifiants(email, password);

            if (userInfo != null) {
                try {
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(userInfo);
                    // Charge la nouvelle fenêtre en cas de succès
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilAdmin-view.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Accueil");
                    stage.setScene(scene);
                    stage.show();

                    // Ferme la fenêtre actuelle
                    stageActuel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Erreur lors du chargement de la nouvelle fenêtre.");
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Une erreur inattendue s'est produite.");
                }
            } else {
                // Affichez un message d'erreur ou effectuez une action en cas d'échec
                Alert alert = new Alert(Alert.AlertType.ERROR, "Identifiants incorrects");
                alert.showAndWait(); // Affichez l'alerte de manière synchrone
            }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait(); // Affiche l'alerte de manière synchrone
    }


    @FXML
    public void lblMotDePasseOublieClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mdpOublie-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Mot de passe oublié");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialise la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @FXML
    public void lblMentionLegalesClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterSalle-view.fxml"));
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

    @FXML
    public void lblContactClicked(Event event) throws IOException {
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



    @FXML
    public void lblInscriptionClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verification-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Inscription");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }
    private void fermerFenetre(ActionEvent actionEvent) {
        // Obtenez la scène (Stage) actuelle à partir de l'objet ActionEvent
        Stage stageActuel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Fermez la fenêtre actuelle
        stageActuel.close();
    }


}